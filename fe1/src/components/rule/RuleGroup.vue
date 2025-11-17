<template>
  <div class="rule-group p-4 border rounded-lg bg-gray-50">
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-xl font-bold text-gray-800">Quy tắc chọn đề tài đồ án</h2>
      <button
        v-if="hasActions"
        @click="openAddRuleModal"
        class="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-offset-2"
      >
        Thêm Quy tắc Mới
      </button>
    </div>
    <BaseTable
      :headers="headers"
      :items="rules"
      item-key="id"
    >
      <template #item-content="{ item }">
        {{ item.content }}
      </template>
      <template #item-actions="{ item }" v-if="hasActions">
        <div class="flex items-center space-x-2">
          <button
            @click="handleEditRule(item)"
            class="px-3 py-1 bg-yellow-500 text-white rounded-md hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-yellow-400 focus:ring-offset-2"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-5 w-5 inline mr-1"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              stroke-width="2"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.536L16.732 3.732z"
              />
            </svg>
            Sửa
          </button>
          <button
            @click="handleDeleteRule(item.id)"
            class="px-3 py-1 bg-red-600 text-white rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-5 w-5 inline mr-1"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              stroke-width="2"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
              />
            </svg>
            Xóa
          </button>
        </div>
      </template>
    </BaseTable>

    <RuleFormModal
      :show="isRuleModalOpen"
      :rule="currentRule"
      :isEditing="isEditingRule"
      @save="handleSaveRuleForm"
      @close="closeRuleModal"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, computed } from 'vue';
import BaseTable from '@/components/common/BaseTable.vue';
import RuleFormModal from './RuleFormModal.vue';

interface Rule {
  id: number;
  content: string;
}

const props = defineProps({
  hasActions: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(['editRule', 'deleteRule']);

const rules = ref<Rule[]>([
  { id: 1, content: 'Đề tài có nhiều phiếu bầu nhất sẽ được chọn.' },
  { id: 2, content: 'Trường hợp hòa phiếu: Đến ngày deadline, nếu có nhiều đề tài có số phiếu bầu bằng nhau, hệ thống sẽ tự động chọn ngẫu nhiên một trong số đó.' },
  { id: 3, content: 'Không có đề tài nào được đề xuất: Nếu không có đề tài nào được sinh viên đề xuất, hệ thống sẽ chọn ngẫu nhiên từ danh sách đề tài trong phần quản lý đề tài. Đồng thời, hệ thống sẽ báo cáo admin về các nhóm có thành viên không làm việc.' },
  { id: 4, content: 'Phiếu bầu mặc định: Đề tài do ai đề xuất thì đã tính là một phiếu bầu cho đề tài đó.' },
  { id: 5, content: 'Không đề xuất và không bỏ phiếu: Nếu sinh viên không đề xuất đề tài nào và cũng không bỏ phiếu, hệ thống sẽ thông báo lại cho admin.' },
  { id: 6, content: 'Chỉ có một đề tài được đề xuất: Nếu chỉ có duy nhất một đề tài được đề xuất, nó sẽ tự động được chọn.' },
  { id: 7, content: 'Số lượng phiếu bầu tối thiểu: Không cần số lượng phiếu bầu tối thiểu.' },
  { id: 8, content: 'Thay đổi phiếu bầu: Sinh viên được phép thay đổi phiếu bầu của mình.' },
  { id: 9, content: 'Chỉnh sửa đề tài đã đề xuất: Sinh viên có thể đề xuất một đề tài mới. Nếu đề tài ban đầu đã nhận được phiếu bầu từ người khác, đề tài ban đầu đó vẫn sẽ được giữ lại và sinh viên có thể thêm một đề xuất đề tài khác.' },
  { id: 10, content: 'Xóa đề tài đã đề xuất: Sinh viên có thể xóa đề tài đã đề xuất nếu chưa có phiếu bầu nào khác và trước deadline.' },
  { id: 11, content: 'Deadline: Hạn deadline do admin thiết lập theo phần Quản lý kế hoạch.' },
  { id: 12, content: 'Quyền của quản trị viên/giảng viên: Giảng viên hoặc quản trị viên không có quyền can thiệp trực tiếp vào quá trình bầu chọn (chọn thủ công, loại bỏ đề tài); họ chỉ có thể yêu cầu thay đổi.' },
  { id: 13, content: 'Số lượng đề xuất tối đa: Mỗi sinh viên chỉ được đề xuất một đề tài.' },
  { id: 14, content: 'Số lượng đề tài được bầu (bỏ phiếu): Mỗi sinh viên chỉ được phép bỏ phiếu cho một đề tài.' },
]);

const headers = computed(() => {
  const baseHeaders = [
    { text: 'ID', value: 'id' },
    { text: 'Nội dung Quy tắc', value: 'content' },
  ];
  if (props.hasActions) {
    baseHeaders.push({ text: 'Hành động', value: 'actions' });
  }
  return baseHeaders;
});

const isRuleModalOpen = ref(false);
const isEditingRule = ref(false);
const currentRule = ref<Rule>({ id: 0, content: '' });

const openAddRuleModal = () => {
  isEditingRule.value = false;
  currentRule.value = { id: 0, content: '' };
  isRuleModalOpen.value = true;
};

const closeRuleModal = () => {
  isRuleModalOpen.value = false;
};

const handleSaveRuleForm = (rule: Rule) => {
  if (isEditingRule.value) {
    const index = rules.value.findIndex(r => r.id === rule.id);
    if (index !== -1) {
      rules.value[index] = { ...rule };
    }
  } else {
    const newId = rules.value.length > 0 ? Math.max(...rules.value.map(r => r.id)) + 1 : 1;
    rules.value.push({ ...rule, id: newId });
  }
  closeRuleModal();
};

const handleEditRule = (rule: Rule) => {
  isEditingRule.value = true;
  currentRule.value = { ...rule };
  isRuleModalOpen.value = true;
};

const handleDeleteRule = (id: number) => {
  if (window.confirm('Bạn có chắc chắn muốn xóa quy tắc này không?')) {
    rules.value = rules.value.filter(rule => rule.id !== id);
  }
};

</script>

<style scoped>
/* Các style đã được chuyển sang Tailwind CSS hoặc BaseTable */
</style>