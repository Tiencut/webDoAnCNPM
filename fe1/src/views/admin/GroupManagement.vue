<template>
  <div class="group-management-container">
    
    <div class="header-controls mb-4">
      <h1 class="group-management-title">Quản lý Nhóm</h1>
      <input
        type="text"
        placeholder="Tìm kiếm nhóm..."
        class="search-input"
        v-model="searchQuery"
      />
      <button @click="openAddGroupModal"
              class="btn btn-primary add-button focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 flex items-center">
        <PlusIcon class="h-1 w-1 mr-1" aria-hidden="true" />
        <span>Thêm Nhóm mới</span>
      </button>
    </div>

    <BaseTable
      :headers="groupTableHeaders"
      :items="filteredGroups"
      item-key="id"
      :has-actions="true"
    >
      <template #item-members="{ item }">
        <div class="member-list">
          <span v-for="member in item.members" :key="member.studentId" class="member-tag cursor-pointer" @click.stop="showStudentDetails(member)">
            {{ member.fullName }}
          </span>
        </div>
      </template>
      <template #actions="{ item }">
        <button @click.stop="requestTopicChange(item)" class="action-button bg-yellow-500 text-white hover:bg-yellow-600 mr-2">Yêu cầu đổi đề tài</button>
        <button @click.stop="deleteGroup(item)" class="action-button bg-red-500 text-white hover:bg-red-600">Xóa</button>
      </template>
    </BaseTable>

    <Modal :show="showGroupModal" @close="closeGroupModal">
      <template #header>
        <h3 class="text-xl font-bold">{{ isEditing ? 'Chỉnh sửa Nhóm' : 'Thêm Nhóm mới' }}</h3>
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

    <Modal :show="showStudentDetailsModal" @close="closeStudentDetailsModal">
      <template #header>
        <h3 class="text-xl font-bold">Chi tiết Sinh viên</h3>
      </template>
      <template #body>
        <StudentDetailsModal v-if="selectedStudent" :student="selectedStudent" />
      </template>
    </Modal>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';
import { PlusIcon } from '@heroicons/vue/24/outline';
import BaseTable from '../../components/common/BaseTable.vue';
import Modal from '../../components/common/Modal.vue';
import GroupForm from '../../components/admin/GroupForm.vue';
import GroupDetails from '../../components/admin/GroupDetails.vue';

interface Student {
  studentId: string;
  fullName: string;
  email: string;
}

interface Group {
  id: number;
  stt: number;
  groupId: string;
  topicName: string;
  note: string;
  members: Student[];
}

const groups = ref<Group[]>([
  {
    id: 1, stt: 1, groupId: 'N001', topicName: 'Đề tài 1', note: 'Ghi chú 1',
    members: [
      { studentId: 'SV001', fullName: 'Nguyễn Văn A', email: 'vana@example.com' },
      { studentId: 'SV002', fullName: 'Trần Thị B', email: 'thib@example.com' },
    ]
  },
  {
    id: 2, stt: 2, groupId: 'N002', topicName: 'Đề tài 2', note: 'Ghi chú 2',
    members: [
      { studentId: 'SV003', fullName: 'Lê Văn C', email: 'vanc@example.com' },
      { studentId: 'SV004', fullName: 'Phạm Thị D', email: 'thid@example.com' },
    ]
  },
]);

const groupTableHeaders = ref([
  { text: 'STT', value: 'stt' },
  { text: 'Mã nhóm', value: 'groupId' },
  { text: 'Thành viên', value: 'members' },
  { text: 'Tên đề tài', value: 'topicName' },
  { text: 'Ghi chú', value: 'note' },
]);

const showGroupModal = ref(false);
const isEditing = ref(false);
const currentGroup = ref<Group | null>(null);
const showStudentDetailsModal = ref(false);
const selectedStudent = ref<Student | null>(null);
const searchQuery = ref('');

const filteredGroups = computed(() => {
  const query = searchQuery.value.toLowerCase();
  if (!query) {
    return groups.value;
  }
  return groups.value.filter(
    (group) =>
      group.groupId.toLowerCase().includes(query) ||
      group.topicName.toLowerCase().includes(query) ||
      group.members.some(member => member.fullName.toLowerCase().includes(query))
  );
});

const emit = defineEmits(['editGroup', 'deleteGroup', 'addGroup', 'viewGroupDetails', 'requestTopicChange']);

const showStudentDetails = (student: Student) => {
  selectedStudent.value = student;
  showStudentDetailsModal.value = true;
};

const closeStudentDetailsModal = () => {
  showStudentDetailsModal.value = false;
  selectedStudent.value = null;
};

const requestTopicChange = (group: Group) => {
  alert(`Yêu cầu đổi đề tài cho nhóm: ${group.topicName} (ID: ${group.groupId})`);
  // Logic to handle topic change request
};

const openAddGroupModal = () => {
  isEditing.value = false;
  currentGroup.value = { id: 0, stt: 0, groupId: '', topicName: '', note: '', members: [] };
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
    groups.value.push(group);
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
  if (confirm(`Bạn có chắc chắn muốn xóa nhóm có mã "${group.groupId}" không?`)) {
    groups.value = groups.value.filter(g => g.id !== group.id);
    alert('Nhóm đã được xóa.');
  }
};

const closeGroupModal = () => {
  showGroupModal.value = false;
  currentGroup.value = null;
};
</script>

<style scoped>
@import '../../components/common/AddButton.css';
.group-management-container {
  padding: 1rem; /* p-4 */
}

.header-controls {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-input {
  padding: 0.5rem 1rem;
  border: 1px solid #d1d5db;
  border-radius: 0.375rem;
  margin-right: 1rem;
  width: 300px;
}

.group-management-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: bold; /* font-bold */
  margin-bottom: 1rem; /* mb-4 */
}


.member-list {
  display: flex;
  flex-direction: column;
  gap: 0.25rem; /* Khoảng cách giữa các thẻ thành viên */
}

.member-tag {
  background-color: #e0e7ff; /* Màu nền nhẹ */
  color: #4338ca; /* Màu chữ */
  padding: 0.25rem 0.75rem; /* Đệm */
  border-radius: 9999px; /* Bo tròn hoàn toàn */
  font-size: 0.875rem; /* Kích thước chữ nhỏ hơn */
  font-weight: 500; /* Độ đậm chữ */
  white-space: nowrap; /* Ngăn không cho thẻ bị ngắt dòng */
  cursor: pointer;
}

.action-button {
  padding: 0.3rem 0.6rem;
  border-radius: 0.375rem; /* rounded-md */
  transition: background-color 0.2s ease-in-out;
}

.action-button.text-blue-600:hover {
  background-color: #dbeafe; /* blue-100 */
}

.action-button.text-yellow-600:hover {
  background-color: #fef3c7; /* yellow-100 */
}

.action-button.text-red-600:hover {
  background-color: #fee2e2; /* red-100 */
}
</style>