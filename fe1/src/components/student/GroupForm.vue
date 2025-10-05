<template>
  <form @submit.prevent="handleSubmit">
    <div class="mb-4">
      <label for="groupId" class="block text-gray-700 text-sm font-bold mb-2">Mã nhóm:</label>
      <input type="text" id="groupId" v-model="formData.groupId" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
    </div>
    <div class="mb-4">
      <label for="topicName" class="block text-gray-700 text-sm font-bold mb-2">Tên đề tài:</label>
      <input type="text" id="topicName" v-model="formData.topicName" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
    </div>
    <div class="mb-4">
      <label for="note" class="block text-gray-700 text-sm font-bold mb-2">Ghi chú:</label>
      <textarea id="note" v-model="formData.note" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"></textarea>
    </div>
    <div class="mb-4">
      <label class="block text-gray-700 text-sm font-bold mb-2">Thành viên:</label>
      <div v-for="(member, index) in formData.members" :key="index" class="flex items-center mb-2">
        <input type="text" v-model="member.name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline mr-2" placeholder="Tên thành viên" required>
        <input type="text" v-model="member.studentId" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline mr-2" placeholder="Mã số sinh viên" required>
        <button type="button" @click="removeMember(index)" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Xóa</button>
      </div>
      <button type="button" @click="addMember" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Thêm thành viên</button>
    </div>
    <div class="flex items-center justify-between">
      <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
        Lưu
      </button>
      <button type="button" @click="emit('close')" class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
        Hủy
      </button>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue';

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
  group: Group | null;
  isEditing: boolean;
}>();

const emit = defineEmits(['save', 'cancel']);

const formData = ref<Group>({
  id: 0,
  stt: 0,
  groupId: '',
  topicName: '',
  note: '',
  members: [],
});

watch(() => props.group, (newGroup) => {
  if (newGroup) {
    formData.value = { ...newGroup };
  } else {
    formData.value = { id: 0, stt: 0, groupId: '', topicName: '', note: '', members: [] };
  }
}, { immediate: true });

const onSubmit = () => {
  emit('save', formData.value);
};
</script>

<style scoped>
/* Các style dành riêng cho form nếu cần */
</style>