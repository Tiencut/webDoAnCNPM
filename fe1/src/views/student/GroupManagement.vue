<template>
  <div class="group-management-container">
    <!-- Nhóm đang đăng ký -->
    <RegisteredGroupList
      :groups="groups"
      :groupTableHeaders="groupTableHeaders"
      :viewGroupDetails="viewGroupDetails"
      @leave-group="handleLeaveGroup"
      @register-topic="handleRegisterTopic"
    />

    <!-- Phân công công việc -->
    <div v-if="currentUserGroupId">
      <TaskManagement
        :assignmentId="currentUserGroupId"
        :userRole="userRole"
        :currentUserName="currentUserName"
      />
    </div>

    <!-- Nộp báo cáo -->
    <ReportSubmissionForm :assignments="mockAssignments" />

    <!-- Danh sách sinh viên khác -->
    <StudentGroupInvitationList
      :students="studentsWithGroupInfo"
      :current-user-group-id="currentUserGroupId"
      @invite-student="handleInviteStudent"
      @request-join-group="handleRequestJoinGroup"
    />

    <Modal :show="showGroupModal" @close="closeGroupModal">
      <template #header>
        <h3 class="text-xl font-bold">{{ isEditing ? 'Chỉnh sửa Nhóm của bạn' : 'Tạo Nhóm mới' }}</h3>
      </template>
      <template #body>
        <GroupForm
          :group="currentGroup"
          :is-editing="isEditing"
          @save="saveGroup"
          @cancel="closeGroupModal"
        />
      </template>
    </Modal>

    <Modal :show="showDetailsModal" @close="closeDetailsModal">
      <template #header>
        <h3 class="text-xl font-bold">Chi tiết Nhóm</h3>
      </template>
      <template #body>
        <GroupDetails
          v-if="currentGroup"
          :group="currentGroup"
          :is-leader="isLeader"
          @approve-request="handleApproveRequest"
          @reject-request="handleRejectRequest"
          @invite-member="handleInviteMember"
          @transfer-leadership="handleTransferLeadership"
        />
      </template>
    </Modal>

    <Modal :show="showTopicRegistrationModal" @close="closeTopicRegistrationModal">
      <template #header>
        <h3 class="text-xl font-bold">Đăng ký/Đề xuất Đề tài Đồ án</h3>
      </template>
      <template #body>
        <TopicRegistrationForm
          :initialTopic="currentTopic"
          @save="saveTopic"
          @cancel="closeTopicRegistrationModal"
        />
      </template>
      <template #footer>
        <div class="flex justify-end space-x-2">
          <button
            type="button"
            @click="closeTopicRegistrationModal"
            class="px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            Hủy
          </button>
          <button
            type="submit"
            @click="handleTopicSave"
            class="px-4 py-2 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
          >
            Lưu
          </button>
        </div>
      </template>
    </Modal>
    <RuleGroup :has-actions="false" />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { useAuthStore } from '../../stores/auth'; // Import auth store
import BaseTable from '../../components/common/BaseTable.vue';
import Modal from '../../components/common/Modal.vue';
import GroupForm from '../../components/student/GroupForm.vue';
import GroupDetails from '../../components/student/GroupDetails.vue';
import AvailableGroupsList from '../../components/student/AvailableGroupsList.vue';
import StudentGroupInvitationList from '../../components/student/StudentGroupInvitationList.vue';
import RegisteredGroupList from "../../components/group/RegisteredGroupList.vue";
import TopicRegistrationForm from '../../components/student/TopicRegistrationForm.vue';
import RuleGroup from '../../components/rule/RuleGroup.vue'; // Import RuleGroup component
import ReportSubmissionForm from '@/components/submission/ReportSubmissionForm.vue';
import TaskManagement from '../../components/task/TaskManagement.vue';

// Note: In <script setup>, components are automatically registered if imported and used.
// The 'components' option is typically not used. This block is added to fulfill the instruction
// as closely as possible, assuming a non-<script setup> context was intended or desired.
// If this component is intended to remain <script setup>, this addition is not standard Vue 3 practice.
// For <script setup>, simply importing the component is sufficient for it to be available in the template.
// This is a placeholder to demonstrate where 'components' would be if this were a standard Options API component.
// If you intend to use Options API, you would need to wrap the script content in 'export default defineComponent({...})'
// and move reactive declarations into 'setup()'.
// components: {
//   BaseTable,
//   Modal,
//   GroupForm,
//   GroupDetails,
//   AvailableGroupsList,
// },

interface Student {
  studentId: string;
  fullName: string;
  email: string;
  groupName?: string; // Thêm thuộc tính này
  groupId?: string; // Thêm thuộc tính này
}

interface Group {
  id: number;
  stt: number;
  groupId: string;
  topicName: string;
  note: string;
  members: Student[];
  leaderId: string; // Thêm thuộc tính leaderId
}

const allStudents = ref<Student[]>([
  { studentId: 'SV001', fullName: 'Nguyễn Văn A', email: 'vana@example.com' },
  { studentId: 'SV002', fullName: 'Trần Thị B', email: 'thib@example.com' },
  { studentId: 'SV003', fullName: 'Lê Văn C', email: 'vanc@example.com' },
  { studentId: 'SV004', fullName: 'Phạm Thị D', email: 'thid@example.com' },
  { studentId: 'SV005', fullName: 'Hoàng Văn E', email: 'vane@example.com' },
  { studentId: 'SV006', fullName: 'Nguyễn Thị F', email: 'thif@example.com' },
]);

const groups = ref<Group[]>([
  {
    id: 1, stt: 1, groupId: 'KH-40', topicName: 'Đề tài 1', note: 'Ghi chú 1',
    members: [
      { studentId: 'SV001', fullName: 'Nguyễn Văn A', email: 'vana@example.com' },
      { studentId: 'SV002', fullName: 'Trần Thị B', email: 'thib@example.com' },
    ]
  },
]);

const studentsWithGroupInfo = computed(() => {
  return allStudents.value.map(student => {
    const foundGroup = groups.value.find(group =>
      group.members.some(member => member.studentId === student.studentId)
    );
    return {
      ...student,
      groupName: foundGroup ? foundGroup.topicName : undefined,
      groupId: foundGroup ? foundGroup.groupId : undefined,
    };
  });
});

const currentUserGroupId = computed(() => {
  // Giả sử sinh viên chỉ thuộc về một nhóm tại một thời điểm
  return groups.value.length > 0 ? groups.value[0].groupId : null;
});

const availableGroups = ref<Group[]>([
  {
    id: 3, stt: 3, groupId: 'N003', topicName: 'Đề tài 3', note: 'Ghi chú 3',
    members: [
      { studentId: 'SV005', fullName: 'Hoàng Văn E', email: 'vane@example.com' },
    ]
  },
  {
    id: 4, stt: 4, groupId: 'N004', topicName: 'Đề tài 4', note: 'Ghi chú 4',
    members: [
      { studentId: 'SV006', fullName: 'Nguyễn Thị F', email: 'thif@example.com' },
    ]
  },
]);

const groupTableHeaders = ref([
  { text: 'STT', value: 'stt' },
  { text: 'Mã nhóm', value: 'groupId' },
  { text: 'Tên đề tài', value: 'topicName' },
  { text: 'Thành viên', value: 'members' },
  { text: 'Ghi chú', value: 'note' },
]);

const showGroupModal = ref(false);
const showDetailsModal = ref(false);
const isEditing = ref(false);
const currentGroup = ref<Group | null>(null);
const isStudentInGroup = ref(false); // Mặc định là false để sinh viên có thể tạo hoặc tham gia nhóm
const showTopicRegistrationModal = ref(false);
const currentTopic = ref<TopicForm | null>(null);
const authStore = useAuthStore();

const userRole = ref('student'); // Giả định vai tròng sinh viên cho chế độ xem này
const currentUserName = computed(() => authStore.userInfo?.fullName || 'Người dùng');

const isLeader = computed(() => {
  return currentGroup.value && authStore.userInfo && currentGroup.value.leaderId === authStore.userInfo.id;
});

const openAddGroupModal = () => {
  isEditing.value = false;
  currentGroup.value = { id: 0, stt: 0, groupId: '', topicName: '', note: '', members: [], leaderId: '' }; // Khởi tạo leaderId
  showGroupModal.value = true;
};

const saveGroup = (group: Group) => {
  if (isEditing.value) {
    const index = groups.value.findIndex(g => g.id === group.id);
    if (index !== -1) {
      groups.value[index] = group;
    }
  } else {
    group.id = groups.value.length > 0 ? Math.max(...groups.value.map(g => g.id)) + 1 : 1;
    // Gán người tạo nhóm làm nhóm trưởng
    const currentUser = authStore.userInfo; // Lấy thông tin người dùng hiện tại
    if (currentUser) {
      group.leaderId = currentUser.id;
      // Thêm người tạo nhóm vào danh sách thành viên nếu chưa có
      const isCreatorInGroup = group.members.some(member => member.studentId === currentUser.id);
      if (!isCreatorInGroup) {
        group.members.push({
          studentId: currentUser.id,
          fullName: currentUser.fullName,
          email: currentUser.email,
        });
      }
    }
    groups.value.push(group);
    isStudentInGroup.value = true; // Sau khi tạo nhóm, sinh viên có nhóm
  }
  closeGroupModal();
  alert('Nhóm đã được lưu thành công.');
};

const editGroup = (group: Group) => {
  isEditing.value = true;
  currentGroup.value = { ...group };
  showGroupModal.value = true;
};

const deleteGroup = (group: Group) => {
  // Students should not be able to delete groups directly
  alert('Bạn không có quyền xóa nhóm.');
};

const closeGroupModal = () => {
  showGroupModal.value = false;
  currentGroup.value = null;
};

const viewGroupDetails = (group: Group) => {
  currentGroup.value = group;
  showDetailsModal.value = true;
};

const closeDetailsModal = () => {
  showDetailsModal.value = false;
  currentGroup.value = null;
};



const handleApproveRequest = (requestId: number) => {
  alert(`Yêu cầu ${requestId} đã được chấp nhận.`);
  // Logic xử lý chấp nhận yêu cầu tham gia nhóm
};

const handleRejectRequest = (requestId: number) => {
  alert(`Yêu cầu ${requestId} đã bị từ chối.`);
  // Logic xử lý từ chối yêu cầu tham gia nhóm
};

const handleInviteMember = (studentId: string) => {
  alert(`Đã gửi lời mời đến sinh viên ${studentId}.`);
  // Logic xử lý mời thành viên
};

const sendJoinRequest = (groupId: number) => {
  alert(`Đã gửi yêu cầu tham gia nhóm ${groupId}`);
  // Logic gửi yêu cầu tham gia nhóm đến backend
};

const handleInviteStudent = (studentId: string) => {
  alert(`Đã gửi lời mời tham gia nhóm đến sinh viên ${studentId}.`);
  // Logic xử lý mời sinh viên vào nhóm
};

const handleRequestJoinGroup = (studentId: string) => {
  alert(`Đã gửi yêu cầu tham gia nhóm của sinh viên ${studentId}.`);
  // Logic xử lý gửi yêu cầu tham gia nhóm của sinh viên khác
};

const handleTransferLeadership = (groupId: number, newLeaderId: string) => {
  const groupIndex = groups.value.findIndex(g => g.id === groupId);
  if (groupIndex !== -1) {
    groups.value[groupIndex].leaderId = newLeaderId;
    alert(`Đã chuyển giao quyền nhóm trưởng cho ${newLeaderId} thành công.`);
    // Cập nhật lại currentGroup nếu nhóm đang được xem chi tiết
    if (currentGroup.value && currentGroup.value.id === groupId) {
      currentGroup.value.leaderId = newLeaderId;
    }
  }
};

const handleLeaveGroup = (group: Group) => {
  if (confirm(`Bạn có chắc chắn muốn rời khỏi nhóm ${group.topicName} không?`)) {
    // Logic để rời nhóm
    alert(`Bạn đã rời khỏi nhóm ${group.topicName}.`);
    // Cập nhật danh sách nhóm sau khi rời
    groups.value = groups.value.filter(g => g.id !== group.id);
  }
};

const handleRegisterTopic = () => {
  currentTopic.value = { topicName: '', note: '' };
  showTopicRegistrationModal.value = true;
};

const saveTopic = (topic: TopicForm) => {
  alert(`Đề tài "${topic.topicName}" với ghi chú "${topic.note}" đã được lưu.`);
  // Logic để lưu đề tài vào backend hoặc cập nhật danh sách đề tài
  showTopicRegistrationModal.value = false;
};

const closeTopicRegistrationModal = () => {
  showTopicRegistrationModal.value = false;
  currentTopic.value = null;
};

interface TopicForm {
  topicName: string;
  note: string;
}

const mockAssignments = ref([
  { id: '1', title: 'Nộp Checklist Self review DB Design', status: 'Chưa nộp' },
  { id: '2', title: 'Nộp checklist Self review SRS', status: 'Chưa nộp' },
  { id: '3', title: 'Nộp Testcase v2.0', status: 'Chưa nộp' },
  { id: '4', title: 'Nộp SRS v3.0', status: 'Chưa nộp' },
  { id: '5', title: 'Nộp Testcase v1.0', status: 'Chưa nộp' },
  { id: '6', title: 'Nộp DB design v2.0', status: 'Chưa nộp' },
  { id: '7', title: 'Nộp DB Design v1.0', status: 'Chưa nộp' },
  { id: '8', title: 'Nộp SRS v2.0', status: 'Chưa nộp' },
  { id: '9', title: 'Nộp SRS v1.0', status: 'Chưa nộp' },
  { id: '10', title: 'Nộp Requirement Outline', status: 'Chưa nộp' },
]);

</script>

<style scoped>
.group-management-container {
  padding: 1rem; /* p-4 */
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.group-management-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.group-management-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: bold; /* font-bold */
  margin-bottom: 1rem; /* mb-4 */
}
</style>
