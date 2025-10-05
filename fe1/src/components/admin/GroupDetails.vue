<template>
  <div class="p-4">
    <h4 class="text-lg font-bold mb-4">Chi tiết Nhóm: {{ group.groupId }} - {{ group.topicName }}</h4>
    <p class="mb-2"><strong>STT:</strong> {{ group.stt }}</p>
    <p class="mb-2"><strong>Ghi chú:</strong> {{ group.note }}</p>

    <h5 class="text-md font-semibold mt-4 mb-2">Thành viên:</h5>
    <ul v-if="group.members && group.members.length > 0" class="list-disc pl-5">
      <li v-for="member in group.members" :key="member.studentId" class="mb-1">
        {{ member.fullName }} ({{ member.studentId }}) - {{ member.email }}
      </li>
    </ul>
    <p v-else>Không có thành viên nào trong nhóm này.</p>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';

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
  group: Group;
}>();
</script>

<style scoped>
/* Có thể thêm các style cụ thể cho GroupDetails tại đây */
</style>