<template>
  <div class="registered-topic-card" v-if="registeredTopic">
    <h2 class="registered-topic-title">Đề tài đã đăng ký</h2>
    <BaseTable :headers="registeredTopicHeaders" :items="registeredTopicDetails" item-key="name" :has-actions="true">
      <template #item-members="{ item }">
        <div v-for="member in item.members" :key="member">{{ member }}</div>
      </template>
      <template #actions="{ item }">
        <button @click="editRegisteredTopic(item)" class="btn-action btn-edit-registered-topic">Sửa tên đề tài</button>
      </template>
    </BaseTable>
  </div>
  <div v-else class="no-registered-topic">
    <p>Bạn chưa đăng ký đề tài nào.</p>
  </div>

  <TopicForm :initialTopic="currentRegisteredTopic" :isEditingProp="true" :show="showEditRegisteredTopicModal" @save="handleSaveRegisteredTopic" @close="handleCancelEditRegisteredTopic" />
</template>

<script setup lang="ts">
import { ref, watch, computed } from 'vue';
import TopicForm from './TopicForm.vue';
import BaseTable from '../common/BaseTable.vue';

interface Topic {
  id: number;
  name: string;
  description: string;
  status: string;
  members: string[];
}

interface TableHeader {
  text: string;
  value: string;
}

const props = defineProps({
  registeredTopic: { type: Object as () => Topic | null, default: null },
});

const emit = defineEmits(['edit-registered-topic', 'save-registered-topic', 'cancel-edit-registered-topic']);

const showEditRegisteredTopicModal = ref(false);
const currentRegisteredTopic = ref<Topic | null>(null);

const registeredTopicHeaders: TableHeader[] = [
  { text: 'Tên đề tài', value: 'name' },
  { text: 'Mô tả', value: 'description' },
  { text: 'Trạng thái', value: 'status' },
  { text: 'Thành viên', value: 'members' },
];

const registeredTopicDetails = computed(() => {
  if (!props.registeredTopic) {
    return [];
  }
  return [
    {
      name: props.registeredTopic.name,
      description: props.registeredTopic.description,
      status: props.registeredTopic.status,
      members: props.registeredTopic.members || [],
    },
  ];
});

watch(() => props.registeredTopic, (newVal) => {
  if (newVal) {
    currentRegisteredTopic.value = { ...newVal };
  }
}, { immediate: true });

function editRegisteredTopic(topic: Topic) {
  currentRegisteredTopic.value = { ...topic };
  showEditRegisteredTopicModal.value = true;
}

function handleSaveRegisteredTopic(updatedTopic: Topic) {
  emit('save-registered-topic', updatedTopic);
  showEditRegisteredTopicModal.value = false;
}

function handleCancelEditRegisteredTopic() {
  showEditRegisteredTopicModal.value = false;
}
</script>

<style scoped>
.registered-topic-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
}

.registered-topic-title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 1rem;
}



.no-registered-topic {
  background-color: #fefcbf; /* yellow-100 */
  color: #8a6d3b; /* yellow-800 */
  padding: 1rem;
  border-radius: 0.5rem;
  margin-bottom: 1.5rem;
  text-align: center;
}

.btn-edit-registered-topic {
  background-color: #3b82f6; /* blue-500 */
  color: #fff;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  font-weight: 600;
  border: none;
  cursor: pointer;
  transition: background-color 0.2s ease-in-out;
  margin-top: 1rem;
}

.btn-edit-registered-topic:hover {
  background-color: #2563eb; /* blue-600 */
}
</style>