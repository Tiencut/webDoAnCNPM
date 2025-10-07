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
      :has-actions="hasActions"
    >
      <template #item-content="{ item }">
        {{ item.content }}
      </template>
      <template #item-actions="{ item }" v-if="hasActions">
        <button
          @click="handleEditRule(item)"
          class="px-3 py-1 bg-yellow-500 text-white rounded-md hover:bg-yellow-600 focus:outline-none focus:ring-2 focus:ring-yellow-400 focus:ring-offset-2 mr-2"
        >
          Sửa
        </button>
        <button
          @click="handleDeleteRule(item.id)"
          class="px-3 py-1 bg-red-600 text-white rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
        >
          Xóa
        </button>
      </template>
    </BaseTable>

    <!-- Add/Edit Rule Modal -->
    <div v-if="isRuleModalOpen" class="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-8 rounded-lg shadow-xl w-full max-w-md">
        <h2 class="text-2xl font-bold mb-4 text-gray-800">{{ isEditingRule ? 'Chỉnh sửa Quy tắc' : 'Thêm Quy tắc Mới' }}</h2>
        <form @submit.prevent="saveRule">
          <div class="mb-4">
            <label for="ruleContent" class="block text-gray-700 text-sm font-bold mb-2">Nội dung Quy tắc:</label>
            <textarea
              id="ruleContent"
              v-model="currentRule.content"
              rows="4"
              class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Nhập nội dung quy tắc"
              required
            ></textarea>
          </div>
          <div class="flex justify-end">
            <button
              type="button"
              @click="closeRuleModal"
              class="mr-4 px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
            >
              Hủy
            </button>
            <button
              type="submit"
              class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              Lưu
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div v-if="isDeleteModalOpen" class="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-8 rounded-lg shadow-xl w-full max-w-sm">
        <h2 class="text-2xl font-bold mb-4 text-gray-800">Xác nhận Xóa</h2>
        <p class="text-gray-700 mb-6">Bạn có chắc chắn muốn xóa quy tắc này không?</p>
        <div class="flex justify-end">
          <button
            type="button"
            @click="cancelDelete"
            class="mr-4 px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200"
          >
            Hủy
          </button>
          <button
            type="button"
            @click="confirmDelete"
            class="px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-red-500"
          >
            Xóa
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, computed } from 'vue';
import BaseTable from '@/components/common/BaseTable.vue';

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
const isDeleteModalOpen = ref(false);
const ruleToDeleteId = ref<number | null>(null);

const openAddRuleModal = () => {
  isEditingRule.value = false;
  currentRule.value = { id: 0, content: '' };
  isRuleModalOpen.value = true;
};

const closeRuleModal = () => {
  isRuleModalOpen.value = false;
};

const saveRule = () => {
  if (isEditingRule.value) {
    const index = rules.value.findIndex(r => r.id === currentRule.value.id);
    if (index !== -1) {
      rules.value[index] = { ...currentRule.value };
    }
  } else {
    const newId = rules.value.length > 0 ? Math.max(...rules.value.map(r => r.id)) + 1 : 1;
    rules.value.push({ ...currentRule.value, id: newId });
  }
  closeRuleModal();
};

const handleEditRule = (rule: Rule) => {
  isEditingRule.value = true;
  currentRule.value = { ...rule };
  isRuleModalOpen.value = true;
};

const handleDeleteRule = (id: number) => {
  ruleToDeleteId.value = id;
  isDeleteModalOpen.value = true;
};

const confirmDelete = () => {
  if (ruleToDeleteId.value !== null) {
    rules.value = rules.value.filter(rule => rule.id !== ruleToDeleteId.value);
    isDeleteModalOpen.value = false;
    ruleToDeleteId.value = null;
  }
};

const cancelDelete = () => {
  isDeleteModalOpen.value = false;
  ruleToDeleteId.value = null;
};

</script>

<style scoped>
/* Các style đã được chuyển sang Tailwind CSS hoặc BaseTable */
</style>