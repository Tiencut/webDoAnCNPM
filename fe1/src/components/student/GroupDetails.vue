<template>
  <div class="p-4">
    <h4 class="text-lg font-bold mb-4">Chi tiết Nhóm: {{ group.groupId }} - {{ group.topicName }}</h4>
    <p class="mb-2"><strong>STT:</strong> {{ group.stt }}</p>
    <p class="mb-2"><strong>Ghi chú:</strong> {{ group.note }}</p>

    <h5 class="text-md font-semibold mt-4 mb-2">Thành viên:</h5>
    <ul v-if="group.members && group.members.length > 0" class="list-disc pl-5">
      <li v-for="member in group.members" :key="member.studentId" class="mb-1">
        {{ member.fullName }} ({{ member.studentId }}) - {{ member.email }}
        <span v-if="member.studentId === group.leaderId" class="ml-2 text-blue-600 font-semibold">(Nhóm trưởng)</span>
      </li>
    </ul>
    <p v-else>Không có thành viên nào trong nhóm này.</p>

    <div v-if="isLeader">
      <h5 class="text-md font-semibold mt-4 mb-2">Yêu cầu tham gia nhóm đang chờ xử lý:</h5>
      <ul v-if="pendingJoinRequests.length > 0" class="list-disc pl-5">
        <li v-for="request in pendingJoinRequests" :key="request.requestId" class="mb-1 flex items-center justify-between">
          {{ request.fullName }} ({{ request.studentId }}) - {{ request.email }}
          <div>
            <button @click="approveRequest(request.requestId)" class="btn btn-success text-white px-2 py-1 rounded-md mr-2">Chấp nhận</button>
            <button @click="rejectRequest(request.requestId)" class="btn btn-danger text-white px-2 py-1 rounded-md">Từ chối</button>
          </div>
        </li>
      </ul>
      <p v-else>Không có yêu cầu tham gia nhóm nào đang chờ xử lý.</p>

      <h5 class="text-md font-semibold mt-4 mb-2">Mời thành viên:</h5>
      <div class="flex">
        <input type="text" placeholder="Nhập ID sinh viên" class="border p-2 rounded-md flex-grow mr-2" />
        <button @click="inviteMember('someStudentId')" class="btn btn-primary text-white px-4 py-2 rounded-md">Mời</button>
      </div>

      <h5 class="text-md font-semibold mt-4 mb-2">Chuyển giao quyền nhóm trưởng:</h5>
      <div class="flex items-center">
        <select v-model="selectedNewLeaderId" class="border p-2 rounded-md flex-grow mr-2">
          <option value="">Chọn thành viên mới</option>
          <option v-for="member in nonLeaderMembers" :key="member.studentId" :value="member.studentId">
            {{ member.fullName }} ({{ member.studentId }})
          </option>
        </select>
        <button @click="transferLeadership" :disabled="!selectedNewLeaderId" class="btn btn-warning text-white px-4 py-2 rounded-md">Chuyển giao</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps, ref, computed } from 'vue';

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
  leaderId: string; // Thêm thuộc tính leaderId
}

const props = defineProps<{
  group: Group;
  isLeader?: boolean; // Thêm prop để xác định trưởng nhóm
}>();

interface JoinRequest {
  requestId: number;
  studentId: string;
  fullName: string;
  email: string;
}

const pendingJoinRequests = ref<JoinRequest[]>([
  { requestId: 1, studentId: 'SV007', fullName: 'Nguyễn Văn G', email: 'vang@example.com' },
  { requestId: 2, studentId: 'SV008', fullName: 'Trần Thị H', email: 'thih@example.com' },
]);

const selectedNewLeaderId = ref<string>('');

const nonLeaderMembers = computed(() => {
  return props.group.members.filter(member => member.studentId !== props.group.leaderId);
});

const emit = defineEmits(['approve-request', 'reject-request', 'invite-member', 'transfer-leadership']);

const approveRequest = (requestId: number) => {
  emit('approve-request', requestId);
};

const rejectRequest = (requestId: number) => {
  emit('reject-request', requestId);
};

const inviteMember = (studentId: string) => {
  emit('invite-member', studentId);
};

const transferLeadership = () => {
  if (selectedNewLeaderId.value && confirm(`Bạn có chắc chắn muốn chuyển giao quyền nhóm trưởng cho ${selectedNewLeaderId.value}?`)) {
    emit('transfer-leadership', props.group.id, selectedNewLeaderId.value);
    selectedNewLeaderId.value = ''; // Reset selection
  }
};

</script>

<style scoped>
/* Có thể thêm các style cụ thể cho GroupDetails tại đây */
</style>