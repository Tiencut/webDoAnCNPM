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
      @edit="editGroup"
      @delete="deleteGroup"
    />

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
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import BaseTable from '../../components/common/BaseTable.vue';
import Modal from '../../components/common/Modal.vue';
import GroupForm from '../../components/admin/GroupForm.vue';

interface Group {
  id: number;
  name: string;
  leader: string;
  membersCount: number;
}

const groups = ref<Group[]>([
  { id: 1, name: 'Nhóm 1', leader: 'Nguyễn Văn A', membersCount: 3 },
  { id: 2, name: 'Nhóm 2', leader: 'Trần Thị B', membersCount: 4 },
]);

const groupTableHeaders = ref([
  { text: 'ID', value: 'id' },
  { text: 'Tên Nhóm', value: 'name' },
  { text: 'Trưởng Nhóm', value: 'leader' },
  { text: 'Số lượng Thành viên', value: 'membersCount' },
]);

const showGroupModal = ref(false);
const isEditing = ref(false);
const currentGroup = ref<Group | null>(null);

const openAddGroupModal = () => {
  isEditing.value = false;
  currentGroup.value = { id: 0, name: '', leader: '', membersCount: 0 };
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