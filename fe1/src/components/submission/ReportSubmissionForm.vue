<template>
  <div class="report-submission-form p-6 bg-white rounded-lg shadow-md mt-6">
    <h2 class="text-xl font-semibold mb-4">Nộp Báo Cáo</h2>

    <div v-if="assignments.length === 0" class="text-gray-600">
      Không có bài tập nào cần nộp vào lúc này.
    </div>

    <div v-else class="overflow-x-auto">
      <BaseTable
        :headers="reportTableHeaders"
        :items="assignmentsWithSubmission"
        item-key="id"
        :has-actions="true"
      >
        <template #item-submissionUrl="{ item }">
          <input
            type="url"
            v-model="item.submissionUrl"
            placeholder="Dán liên kết vào đây"
            class="w-full px-2 py-1 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
        </template>
        <template #item-status="{ item }">
          <span :class="getStatusClass(item.status)">
            {{ item.status }}
          </span>
        </template>
        <template #actions="{ item }">
          <button
            @click="submitReport(item)"
            :disabled="!item.submissionUrl || item.status === 'Đang xử lý'"
            class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Nộp
          </button>
        </template>
      </BaseTable>
    </div>

    <p v-if="globalMessage" :class="globalMessageClass" class="mt-3 text-sm">{{ globalMessage }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, defineProps } from 'vue';
import BaseTable from '../../components/common/BaseTable.vue'; // Import BaseTable

interface Assignment {
  id: string;
  title: string;
  status: 'Chưa nộp' | 'Đã nộp' | 'Đang xử lý' | 'Lỗi';
  submissionUrl?: string; // Optional, as it might not be submitted yet
}

interface AssignmentWithSubmission extends Assignment {
  submissionUrl: string;
}

const props = defineProps({
  assignments: {
    type: Array as () => Assignment[],
    required: true,
  },
});

const reportTableHeaders = ref([
  { text: 'Bài Tập', value: 'title' },
  { text: 'Liên Kết Nộp Bài', value: 'submissionUrl' },
  { text: 'Trạng Thái', value: 'status' },
]);

// Create a reactive list that includes submission URLs and local status management
const assignmentsWithSubmission = ref<AssignmentWithSubmission[]>(
  props.assignments.map(assignment => ({
    ...assignment,
    submissionUrl: assignment.submissionUrl || '',
  }))
);

const globalMessage = ref<string>('');
const globalMessageType = ref<'success' | 'error' | ''>('');

const globalMessageClass = computed(() => {
  if (globalMessageType.value === 'success') {
    return 'text-green-600';
  } else if (globalMessageType.value === 'error') {
    return 'text-red-600';
  }
  return '';
});

const getStatusClass = (status: Assignment['status']) => {
  switch (status) {
    case 'Đã nộp':
      return 'px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800';
    case 'Lỗi':
      return 'px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800';
    case 'Đang xử lý':
      return 'px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800';
    case 'Chưa nộp':
    default:
      return 'px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-gray-100 text-gray-800';
  }
};

const submitReport = (assignment: AssignmentWithSubmission) => {
  if (!assignment.submissionUrl) {
    globalMessage.value = `Vui lòng nhập liên kết cho bài tập "${assignment.title}".`;
    globalMessageType.value = 'error';
    return;
  }

  // Basic URL validation (can be enhanced)
  try {
    new URL(assignment.submissionUrl);
  } catch (e) {
    globalMessage.value = `Liên kết cho bài tập "${assignment.title}" không hợp lệ. Vui lòng kiểm tra lại.`;
    globalMessageType.value = 'error';
    return;
  }

  // Update local status to 'Đang xử lý'
  const index = assignmentsWithSubmission.value.findIndex(a => a.id === assignment.id);
  if (index !== -1) {
    assignmentsWithSubmission.value[index].status = 'Đang xử lý';
    globalMessage.value = `Đang nộp bài tập "${assignment.title}"...`;
    globalMessageType.value = '';
  }

  // TODO: Gửi assignment.id và assignment.submissionUrl này lên Backend
  console.log(
    `Nộp báo cáo cho bài tập: ${assignment.title} (ID: ${assignment.id}) với URL: ${assignment.submissionUrl}`
  );

  // Simulate API call
  setTimeout(() => {
    if (Math.random() > 0.2) { // 80% success rate
      if (index !== -1) {
        assignmentsWithSubmission.value[index].status = 'Đã nộp';
        globalMessage.value = `Bài tập "${assignment.title}" đã được nộp thành công.`;
        globalMessageType.value = 'success';
      }
    } else {
      if (index !== -1) {
        assignmentsWithSubmission.value[index].status = 'Lỗi';
        globalMessage.value = `Có lỗi xảy ra khi nộp bài tập "${assignment.title}". Vui lòng thử lại.`;
        globalMessageType.value = 'error';
      }
    }
  }, 1500);
};
</script>

<style scoped>
/* Có thể thêm các style tùy chỉnh nếu cần */
</style>