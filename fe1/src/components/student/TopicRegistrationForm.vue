<template>
  <form @submit.prevent="handleSubmit" class="space-y-4">
    <div>
      <label for="topicName" class="block text-sm font-medium text-gray-700">Tên đề tài</label>
      <input
        type="text"
        id="topicName"
        v-model="form.topicName"
        class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
        required
      />
    </div>
    <div>
      <label for="note" class="block text-sm font-medium text-gray-700">Ghi chú</label>
      <textarea
        id="note"
        v-model="form.note"
        rows="3"
        class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
      ></textarea>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';

interface TopicForm {
  topicName: string;
  note: string;
}

const props = defineProps<{
  initialTopic?: TopicForm;
}>();

const emit = defineEmits<{
  (e: 'save', form: TopicForm): void;
  (e: 'cancel'): void;
}>();

const form = ref<TopicForm>({
  topicName: props.initialTopic?.topicName || '',
  note: props.initialTopic?.note || '',
});

watch(() => props.initialTopic, (newVal) => {
  if (newVal) {
    form.value = { ...newVal };
  }
}, { deep: true });

const handleSubmit = () => {
  emit('save', form.value);
};
</script>

<style scoped>
.space-y-4 > div {
  margin-bottom: 1rem; /* Thêm khoảng cách dưới cho mỗi trường form */
}

.space-y-4 > div:last-child {
  margin-bottom: 0; /* Loại bỏ khoảng cách dưới cho div cuối cùng (chứa nút) */
}

.flex.justify-end.space-x-2 {
  margin-top: 1.5rem; /* Thêm khoảng cách trên cho phần nút */
}
</style>