<template>
  <div class="student-group-invitation-list">
    <h2 class="text-xl font-bold mb-4 mt-8">Danh sách sinh viên khác</h2>
    <BaseTable
      :headers="studentTableHeaders"
      :items="studentsWithProcessedStatus"
      item-key="studentId"
      :has-actions="false"
    >
      <template #item-actions="{ item }">
        <div class="flex space-x-2">
          <button
            v-if="shouldShowInviteButton(item)"
            @click="handleInviteStudentClick(item.studentId)"
            class="btn btn-sm bg-green-500 hover:bg-green-600 text-white py-1 px-2 rounded"
          >
            Mời tham gia
          </button>
          <button
            v-if="shouldShowRequestButton(item)"
            @click="emit('requestJoinGroup', item.studentId)"
            class="btn btn-sm bg-blue-500 hover:bg-blue-600 text-white py-1 px-2 rounded"
          >
            Gửi yêu cầu tham gia nhóm của họ
          </button>
        </div>
      </template>
    </BaseTable>
  </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, computed } from 'vue';
import BaseTable from '../common/BaseTable.vue';

interface Student {
  studentId: string;
  fullName: string;
  groupName?: string; // Tên nhóm nếu sinh viên đã có nhóm
  groupId?: string; // ID nhóm nếu sinh viên đã có nhóm
}

const props = defineProps<{
  students: Student[];
  currentUserGroupId: string | null; // ID nhóm của người dùng hiện tại, null nếu chưa có nhóm
}>();

const emit = defineEmits(['inviteStudent', 'requestJoinGroup']);

const studentTableHeaders = [
  { text: 'STT', value: 'stt' },
  { text: 'Mã sinh viên', value: 'studentId' },
  { text: 'Tên sinh viên', value: 'fullName' },
  { text: 'Trạng thái nhóm', value: 'groupStatus' },
  { text: 'Hành động', value: 'actions' },
];

const studentsWithProcessedStatus = computed(() => {
  return props.students.map((student, index) => ({
    ...student,
    stt: index + 1,
    groupStatus: student.groupName || 'Chưa có nhóm',
  }));
});

const handleInviteStudentClick = (studentId: string) => {
  if (confirm('Bạn có chắc chắn muốn mời sinh viên này tham gia nhóm không?')) {
    emit('inviteStudent', studentId);
  }
};

const shouldShowInviteButton = (student: Student) => {
  // Chỉ hiển thị nút "Mời tham gia" nếu sinh viên chưa có nhóm và người dùng hiện tại có nhóm
  return !student.groupName && props.currentUserGroupId !== null;
};

const shouldShowRequestButton = (student: Student) => {
  // Chỉ hiển thị nút "Gửi yêu cầu tham gia nhóm của họ" nếu sinh viên đã có nhóm
  // và sinh viên đó không thuộc nhóm của người dùng hiện tại
  return student.groupName && student.groupId !== props.currentUserGroupId;
};
</script>

<style scoped>
/* Có thể thêm CSS tùy chỉnh tại đây nếu cần */
</style>