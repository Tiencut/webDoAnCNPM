<template>
  <div class="task-management">
    <h2 class="section-title">Quản lý phân công công việc</h2>

    <!-- Form thêm nhiệm vụ mới -->
    <div class="task-form-container" v-if="showAddTaskForm">
      <div class="modal-overlay" v-if="showAddTaskForm">
        <div class="modal-content">
          <button @click="showAddTaskForm = false" class="close-modal-btn">&times;</button>
          <h3>Thêm nhiệm vụ mới</h3>
          <form @submit.prevent="addTask" class="task-form">
            <div class="form-group">
              <label for="taskName">Tên nhiệm vụ:</label>
              <input type="text" id="taskName" v-model="newTask.name" required />
            </div>
            <div class="form-group">
              <label for="taskDescription">Mô tả:</label>
              <textarea id="taskDescription" v-model="newTask.description"></textarea>
            </div>
            <div class="form-group">
              <label for="taskDueDate">Ngày đến hạn:</label>
              <input type="date" id="taskDueDate" v-model="newTask.dueDate" />
            </div>
            <div class="form-group">
              <label for="taskAssignedTo">Người được giao:</label>
              <select id="taskAssignedTo" v-model="newTask.assignedTo" required>
                <option value="">Chọn thành viên</option>
                <option v-for="member in teamMembers" :key="member.id" :value="member.name">{{ member.name }}</option>
              </select>
            </div>
            <div class="form-group">
              <label for="taskType">Loại công việc:</label>
              <select id="taskType" v-model="newTask.type" required>
                <option value="">Chọn loại công việc</option>
                <option value="RO">Requirement Outline (RO)</option>
                <option value="SRS">Software Requirements Specification (SRS)</option>
                <option value="DB Design">Thiết kế cơ sở dữ liệu (DB Design)</option>
                <option value="UI/UX Design">Thiết kế giao diện người dùng (UI/UX Design)</option>
                <option value="System Architecture Design">Thiết kế kiến trúc hệ thống</option>
                <option value="Test Case">Test Case</option>
              </select>
            </div>
            <div class="form-group">
              <label for="documentLink">Liên kết tài liệu:</label>
              <input type="url" id="documentLink" v-model="newTask.documentLink" placeholder="URL đến tài liệu" />
            </div>
            <div class="form-group">
              <label for="version">Phiên bản:</label>
              <input type="text" id="version" v-model="newTask.version" placeholder="Ví dụ: 1.0" />
            </div>
            <div class="form-group" v-if="userRole === 'teamLeader' || userRole === 'lecturer'">
              <label for="approvalStatus">Trạng thái phê duyệt:</label>
              <select id="approvalStatus" v-model="newTask.approvalStatus">
                <option value="Pending">Chờ duyệt</option>
                <option value="Approved">Đã duyệt</option>
                <option value="Needs Revision">Cần sửa đổi</option>
              </select>
            </div>
            <div class="form-group" v-if="userRole === 'teamLeader' || userRole === 'lecturer'">
              <label for="approver">Người duyệt:</label>
              <input type="text" id="approver" v-model="newTask.approver" placeholder="Tên người duyệt" />
            </div>
            <div class="form-group" v-if="userRole === 'teamLeader' || userRole === 'lecturer'">
              <label for="feedback">Ghi chú/Phản hồi:</label>
              <textarea id="feedback" v-model="newTask.feedback" placeholder="Ghi chú hoặc phản hồi"></textarea>
            </div>
            <button type="submit" class="btn-primary">Thêm nhiệm vụ</button>
          </form>
        </div>
      </div>
    </div>

    <!-- Danh sách nhiệm vụ -->
    <div class="task-list-container">
      <h3>Danh sách nhiệm vụ</h3>
      <button @click="showAddTaskForm = true" class="btn-primary toggle-form-btn">
        Thêm nhiệm vụ mới
      </button>
      <table class="task-table">
        <thead>
          <tr>
            <th>Tên nhiệm vụ</th>
            <th>Mô tả</th>
            <th>Ngày đến hạn</th>
            <th>Người được giao</th>
            <th>Loại công việc</th>
            <th>Liên kết tài liệu</th>
            <th>Phiên bản</th>
            <th v-if="userRole === 'teamLeader' || userRole === 'lecturer'">Trạng thái phê duyệt</th>
            <th v-if="userRole === 'teamLeader' || userRole === 'lecturer'">Người duyệt</th>
            <th v-if="userRole === 'teamLeader' || userRole === 'lecturer'">Ghi chú/Phản hồi</th>
            <th>Trạng thái</th>
            <th>Tiến độ thành viên</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in tasks" :key="task.id">
            <td>{{ task.name }}</td>
            <td>{{ task.description }}</td>
            <td>{{ task.dueDate }}</td>
            <td>{{ task.assignedTo }}</td>
            <td>{{ task.type }}</td>
            <td>
              <a v-if="task.documentLink" :href="task.documentLink" target="_blank">Xem tài liệu</a>
              <span v-else>N/A</span>
            </td>
            <td>{{ task.version || 'N/A' }}</td>
            <td v-if="userRole === 'teamLeader' || userRole === 'lecturer'">{{ task.approvalStatus || 'N/A' }}</td>
            <td v-if="userRole === 'teamLeader' || userRole === 'lecturer'">{{ task.approver || 'N/A' }}</td>
            <td v-if="userRole === 'teamLeader' || userRole === 'lecturer'">{{ task.feedback || 'N/A' }}</td>
            <td>
              <select
                v-model="task.status"
                @change="updateTaskStatus(task)"
                :disabled="!(userRole === 'lecturer' || userRole === 'teamLeader' || task.assignedTo === currentUserName)"
                :title="!(userRole === 'lecturer' || userRole === 'teamLeader' || task.assignedTo === currentUserName) ? 'Bạn không có quyền thay đổi trạng thái nhiệm vụ này.' : ''"
              >
                <option value="pending">Chưa bắt đầu</option>
                <option value="in-progress">Đang tiến hành</option>
                <option value="completed">Hoàn thành</option>
                <option value="blocked">Bị chặn</option>
              </select>
            </td>
            <td>
              <div v-if="teamMembersProgress[task.assignedTo]">
                Tổng: {{ teamMembersProgress[task.assignedTo].total }}<br>
                Hoàn thành: {{ teamMembersProgress[task.assignedTo].completed }}<br>
                Đang tiến hành: {{ teamMembersProgress[task.assignedTo].inProgress }}<br>
                Chưa bắt đầu: {{ teamMembersProgress[task.assignedTo].pending }}<br>
                Bị chặn: {{ teamMembersProgress[task.assignedTo].blocked }}
              </div>
              <div v-else>N/A</div>
            </td>
            <td>
              <button @click="editTask(task)" class="btn-secondary" v-if="userRole === 'teamLeader'">Sửa</button>
              <button @click="deleteTask(task.id)" class="btn-danger" v-if="userRole === 'teamLeader'">Xóa</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, defineProps, watch } from 'vue';

const showAddTaskForm = ref(false);
// const userRole = ref(localStorage.getItem('userRole') || 'student'); // Lấy vai tròng người dùng từ localStorage
// const currentUserName = ref(localStorage.getItem('userName') || ''); // Lấy tên người dùng hiện tại từ localStorage

interface Task {
  id: string;
  name: string;
  description: string;
  status: 'pending' | 'in-progress' | 'completed' | 'blocked';
  priority: 'high' | 'medium' | 'low';
  type: 'Yêu cầu' | 'Thiết kế' | 'Kiểm thử' | 'Khác';
  documentLink: string;
  version: string;
  approvalStatus: 'Pending' | 'Approved' | 'Needs Revision';
  approver: string;
  feedback: string;
  dueDate: string;
  assignedTo: string;
  assignmentId: string; // Thêm trường assignmentId
}

const props = defineProps<{
  assignmentId: string;
  userRole: string;
  currentUserName: string;
}>();

interface TeamMember {
  id: number;
  name: string;
}

// Dữ liệu mockTasks sẽ được lọc dựa trên assignmentId
const allMockTasks = ref<Task[]>([
  { id: '1', name: 'Xây dựng tài liệu RO', description: 'Xác định các yêu cầu chức năng và phi chức năng', dueDate: '2024-06-30', assignedTo: 'Nguyễn Văn A', status: 'in-progress', type: 'Yêu cầu', documentLink: 'https://docs.google.com/document/d/ro_doc', version: '1.0', approvalStatus: 'Chờ duyệt', approver: '', feedback: '', assignmentId: '10' },
  { id: '2', name: 'Thiết kế cơ sở dữ liệu', description: 'Thiết kế các bảng và mối quan hệ', dueDate: '2024-07-05', assignedTo: 'Trần Thị B', status: 'pending', type: 'Thiết kế', documentLink: 'https://docs.google.com/document/d/db_design', version: '0.9', approvalStatus: 'Chờ duyệt', approver: '', feedback: '', assignmentId: '10' },
  { id: '3', name: 'Viết Test Cases cho chức năng đăng nhập', description: 'Viết các trường hợp kiểm thử cho chức năng đăng nhập', dueDate: '2024-07-10', assignedTo: 'Lê Văn C', status: 'completed', type: 'Kiểm thử', documentLink: 'https://docs.google.com/document/d/test_cases', version: '1.0', approvalStatus: 'Đã duyệt', approver: 'Giảng viên A', feedback: 'Tốt', assignmentId: '1' },
  { id: '4', name: 'Cập nhật SRS', description: 'Cập nhật tài liệu SRS theo phản hồi', dueDate: '2024-07-15', assignedTo: 'Nguyễn Văn A', status: 'pending', type: 'Yêu cầu', documentLink: 'https://docs.google.com/document/d/srs_doc', version: '1.1', approvalStatus: 'Chờ duyệt', approver: '', feedback: '', assignmentId: '2' },
  { id: '5', name: 'Kiểm tra SRS', description: 'Kiểm tra tài liệu SRS', dueDate: '2024-07-16', assignedTo: 'Trần Thị B', status: 'pending', type: 'Yêu cầu', documentLink: 'https://docs.google.com/document/d/srs_doc_check', version: '1.1', approvalStatus: 'Chờ duyệt', approver: '', feedback: '', assignmentId: '2' },
]);

const tasks = computed(() => {
  return allMockTasks.value.filter(task => task.assignmentId === props.assignmentId);
});

const teamMembers = ref<TeamMember[]>([
  { id: 1, name: 'Nguyễn Văn A' },
  { id: 2, name: 'Trần Thị B' },
  { id: 3, name: 'Lê Văn C' },
  { id: 4, name: 'Phạm Thị D' },
]);

const newTask = ref<Omit<Task, 'id' | 'assignmentId'>>({
  name: '',
  description: '',
  dueDate: '',
  assignedTo: '',
  status: 'pending',
  type: 'RO', // Default type
  documentLink: '',
  version: '',
  approvalStatus: 'Pending',
  approver: '',
  feedback: '',
});

let nextTaskId = allMockTasks.value.length > 0 ? Math.max(...allMockTasks.value.map(task => parseInt(task.id))) + 1 : 1;

const addTask = () => {
  if (newTask.value.name && newTask.value.assignedTo && newTask.value.type) {
    allMockTasks.value.push({
      ...newTask.value,
      id: String(nextTaskId++),
      assignmentId: props.assignmentId, // Gán assignmentId từ props
    });
    // Reset form
    newTask.value = {
      name: '',
      description: '',
      dueDate: '',
      assignedTo: '',
      status: 'pending',
      type: 'RO', // Reset to default type
      documentLink: '',
      version: '',
      approvalStatus: 'Pending',
      approver: '',
      feedback: '',
    };
    showAddTaskForm.value = false; // Đóng form sau khi thêm
  }
};

const editTask = (task: Task) => {
  // Implement edit logic here, e.g., open a modal with task details
  alert(`Chỉnh sửa nhiệm vụ: ${task.name}`);
};

const deleteTask = (id: string) => {
  allMockTasks.value = allMockTasks.value.filter(task => task.id !== id);
};

const updateTaskStatus = (task: Task) => {
  if (props.userRole === 'lecturer' || props.userRole === 'teamLeader' || task.assignedTo === props.currentUserName) {
    // In a real application, you would send this update to a backend API
    console.log(`Cập nhật trạng thái nhiệm vụ ${task.name} thành ${task.status}`);
  } else {
    alert('Bạn không có quyền thay đổi trạng thái của nhiệm vụ này.');
    // Revert the status change in UI if not authorized
    const originalTask = allMockTasks.value.find(t => t.id === task.id);
    if (originalTask) {
      task.status = originalTask.status;
    }
  }
};

const teamMembersProgress = computed(() => {
  const progress: { [key: string]: { total: number; completed: number; inProgress: number; pending: number; blocked: number } } = {};

  teamMembers.value.forEach(member => {
    progress[member.name] = { total: 0, completed: 0, inProgress: 0, pending: 0, blocked: 0 };
  });

  tasks.value.forEach(task => { // Sử dụng tasks đã lọc
    if (progress[task.assignedTo]) {
      progress[task.assignedTo].total++;
      if (task.status === 'completed') {
        progress[task.assignedTo].completed++;
      } else if (task.status === 'in-progress') {
        progress[task.assignedTo].inProgress++;
      } else if (task.status === 'pending') {
        progress[task.assignedTo].pending++;
      } else if (task.status === 'blocked') {
        progress[task.assignedTo].blocked++;
      }
    }
  });

  return progress;
});
</script>

<style scoped>
.task-management {
  font-family: Arial, sans-serif;
}

.section-title {
  font-size: 1.8rem;
  font-weight: bold;
  color: #0056b3;
  margin-bottom: 20px;
  border-bottom: 2px solid #0056b3;
  padding-bottom: 5px;
}

.task-form-container,
.task-list-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}

.task-form-container h3,
.task-list-container h3 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 15px;
}

.task-form .form-group {
  margin-bottom: 15px;
}

.task-form label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.task-form input[type="text"],
.task-form input[type="date"],
.task-form textarea,
.task-form select {
  width: calc(100% - 22px);
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}

.task-form textarea {
  resize: vertical;
  min-height: 80px;
}

.btn-primary,
.btn-secondary,
.btn-danger {
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  margin-right: 10px;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #5a6268;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-danger:hover {
  background-color: #c82333;
}

.task-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

.task-table th,
.task-table td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

.task-table th {
  background-color: #e9ecef;
  font-weight: bold;
  color: #495057;
}

.task-table tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}

.task-table tbody tr:hover {
  background-color: #e2e6ea;
}

.task-table td select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: #fff;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 90%;
  max-width: 500px;
  position: relative;
}

.close-modal-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #333;
}

.close-modal-btn:hover {
  color: #000;
}

.toggle-form-btn {
  margin-bottom: 20px;
}

.member-progress-summary {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}

.member-progress-summary h3 {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 15px;
}

.progress-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

.progress-table th,
.progress-table td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

.progress-table th {
  background-color: #e9ecef;
  font-weight: bold;
  color: #495057;
}

.progress-table tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}

.progress-table tbody tr:hover {
  background-color: #e2e6ea;
}
</style>