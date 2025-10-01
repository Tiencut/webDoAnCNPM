<template>
  <form @submit.prevent="onSubmit" class="p-4">
    <div class="mb-4">
      <label for="groupName" class="block text-gray-700 text-sm font-bold mb-2">Tên Nhóm:</label>
      <input
        type="text"
        id="groupName"
        v-model="formData.name"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        required
      />
    </div>

    <div class="mb-4">
      <label for="groupLeader" class="block text-gray-700 text-sm font-bold mb-2">Trưởng Nhóm:</label>
      <input
        type="text"
        id="groupLeader"
        v-model="formData.leader"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        required
      />
    </div>

    <div class="mb-4">
      <label for="groupMembers" class="block text-gray-700 text-sm font-bold mb-2">Số lượng Thành viên:</label>
      <input
        type="number"
        id="groupMembers"
        v-model="formData.membersCount"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        required
      />
    </div>

    <div class="flex items-center justify-between">
      <button
        type="submit"
        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
      >
        {{ isEditing ? 'Cập nhật' : 'Thêm' }}
      </button>
      <button
        type="button"
        @click="$emit('cancel')"
        class="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
      >
        Hủy
      </button>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue';

interface Group {
  id: number;
  name: string;
  leader: string;
  membersCount: number;
}

const props = defineProps<{
  group: Group | null;
  isEditing: boolean;
}>();

const emit = defineEmits(['save', 'cancel']);

const formData = ref<Group>({
  id: 0,
  name: '',
  leader: '',
  membersCount: 0,
});

watch(() => props.group, (newGroup) => {
  if (newGroup) {
    formData.value = { ...newGroup };
  } else {
    formData.value = { id: 0, name: '', leader: '', membersCount: 0 };
  }
}, { immediate: true });

const onSubmit = () => {
  emit('save', formData.value);
};
</script>

<style scoped>
/* Các style dành riêng cho form nếu cần */
</style>