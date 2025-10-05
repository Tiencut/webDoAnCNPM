<template>
  <div class="group-management-container">
    <h1 class="group-management-title">Quản lý Nhóm</h1>

    <div class="flex justify-end mb-4">
      <button @click="openAddGroupModal"
              class="btn btn-primary py-2 px-4 rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
        Thêm Nhóm mới
      </button>
    </div>

    <BaseTable
      :headers="groupTableHeaders"
      :items="groups"
      item-key="id"
      :has-actions="true"
      :clickable="true"
      @row-click="editGroup"
    >
      <template #actions="{ item }">
        <button @click.stop="deleteGroup(item)" class="text-red-600 hover:text-red-900">Xóa</button>
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

    <Modal :show="showDetailsModal" @close="closeDetailsModal">
      <template #header>
        <h3 class="text-xl font-bold">Chi tiết Nhóm</h3>
      </template>
      <template #body>
        <GroupDetails v-if="currentGroup" :group="currentGroup" />
      </template>
    </Modal>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
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
  { text: 'Tên đề tài', value: 'topicName' },
  { text: 'Ghi chú', value: 'note' },
  { text: 'Hành động', value: 'actions' },
]);

const showGroupModal = ref(false);
const showDetailsModal = ref(false);
const isEditing = ref(false);
const currentGroup = ref<Group | null>(null);

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
  if (confirm(`Bạn có chắc chắn muốn xóa nhóm "${group.name}"?`)) {
    groups.value = groups.value.filter(g => g.id !== group.id);
    alert('Nhóm đã được xóa.');
  }
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
</script>

<style scoped>
.group-management-container {
  padding: 1rem; /* p-4 */
}

.group-management-title {
  font-size: 1.5rem; /* text-2xl */
  font-weight: bold; /* font-bold */
  margin-bottom: 1rem; /* mb-4 */
}
</style>