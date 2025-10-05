<template>
  <div class="available-groups-list">
    <h3 class="text-lg font-semibold mb-4">Các nhóm có sẵn để tham gia</h3>
    <div v-if="availableGroups.length === 0" class="text-gray-600">
      Không có nhóm nào có sẵn để tham gia.
    </div>
    <ul v-else class="space-y-2">
      <li v-for="group in availableGroups" :key="group.id" class="bg-gray-50 p-3 rounded-md shadow-sm flex justify-between items-center">
        <div>
          <p class="font-medium">{{ group.topicName }} (Mã nhóm: {{ group.groupId }})</p>
          <p class="text-sm text-gray-500">Thành viên: {{ group.members.length }}</p>
        </div>
        <button @click="viewGroupDetails(group)" class="btn btn-sm btn-info">Xem chi tiết</button>
        <button @click="sendJoinRequest(group.id)" class="btn btn-sm btn-primary ml-2">Gửi yêu cầu tham gia</button>
      </li>
    </ul>

    <Modal :show="showDetailsModal" @close="closeDetailsModal">
      <template #header>
        <h3 class="text-xl font-bold">Chi tiết Nhóm</h3>
      </template>
      <template #body>
        <GroupDetails v-if="selectedGroup" :group="selectedGroup" />
      </template>
    </Modal>
  </div>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits } from 'vue';
import Modal from '../../components/common/Modal.vue';
import GroupDetails from './GroupDetails.vue';

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

const props = defineProps<{
  availableGroups: Group[];
}>();

const emit = defineEmits(['sendJoinRequest']);

const showDetailsModal = ref(false);
const selectedGroup = ref<Group | null>(null);

const viewGroupDetails = (group: Group) => {
  selectedGroup.value = group;
  showDetailsModal.value = true;
};

const closeDetailsModal = () => {
  showDetailsModal.value = false;
  selectedGroup.value = null;
};

const sendJoinRequest = (groupId: number) => {
  emit('sendJoinRequest', groupId);
};
</script>

<style scoped>
.available-groups-list {
  margin-top: 1.5rem;
  padding: 1rem;
  border: 1px solid #e2e8f0;
  border-radius: 0.5rem;
  background-color: #ffffff;
}

.btn-sm {
  padding: 0.25rem 0.75rem;
  font-size: 0.875rem;
}

.btn-info {
  background-color: #3b82f6;
  color: white;
  border-radius: 0.25rem;
}

.btn-info:hover {
  background-color: #2563eb;
}

.btn-primary {
  background-color: #4CAF50;
  color: white;
  border-radius: 0.25rem;
}

.btn-primary:hover {
  background-color: #45a049;
}
</style>