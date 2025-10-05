<template>
  <div class="overflow-x-auto">
    <h2 class="text-xl font-bold mb-4">Nhóm của bạn</h2>
    <BaseTable
      :headers="groupTableHeaders"
      :items="groups"
      item-key="id"
      :has-actions="true"
      :clickable="true"
      @row-click="viewGroupDetails"
    >
      <!-- Removed delete action for students -->
      <template #item-members="{ item }">
        <ul class="list-none list-inside">
          <li v-for="member in item.members" :key="member.studentId">{{ member.fullName }}</li>
        </ul>
      </template>
      <template #actions="{ item }">
        <button @click.stop="emit('leave-group', item)" class="btn btn-danger py-1 px-2 rounded-md shadow-sm text-white bg-red-600 hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500">
          Rời nhóm
        </button>
      </template>
    </BaseTable>
  </div>
</template>

<script setup lang="ts">
import BaseTable from '../common/BaseTable.vue';
import { defineProps, defineEmits } from 'vue';

const emit = defineEmits(['leave-group']);

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
  leaderId: string;
}

interface GroupTableHeader {
  text: string;
  value: string;
}

const props = defineProps<{
  groups: Group[];
  groupTableHeaders: GroupTableHeader[];
  viewGroupDetails: (group: Group) => void;
}>();
</script>

<style scoped></style>