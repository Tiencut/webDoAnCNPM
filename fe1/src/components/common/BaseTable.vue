<template>
  <table class="data-table">
    <thead class="table-header">
      <tr>
        <th v-for="header in headers" :key="header.value" scope="col" class="table-th">{{ header.text }}</th>
        <th v-if="hasActions" scope="col" class="table-th">Hành động</th>
      </tr>
    </thead>
    <tbody class="table-body">
      <tr v-for="item in items" :key="item[itemKey]">
        <td v-for="header in headers" :key="header.value" class="table-td">{{ item[header.value] }}</td>
        <td v-if="hasActions" class="table-td action-buttons">
          <button @click="$emit('edit', item)" class="btn-edit">Sửa</button>
          <button @click="$emit('delete', item[itemKey])" class="btn-delete">Xóa</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, computed } from 'vue';

interface TableHeader {
  text: string;
  value: string;
}

const props = defineProps({
  headers: {
    type: Array as () => TableHeader[],
    required: true,
  },
  items: {
    type: Array as () => any[],
    required: true,
  },
  itemKey: {
    type: String,
    required: true,
  },
  hasActions: {
    type: Boolean,
    default: true,
  },
});

const emits = defineEmits(['edit', 'delete']);
</script>

<style scoped>
/* BaseTable.vue */
.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.table-header {
  background-color: #f4f6f8;
}

.table-th {
  padding: 12px 15px;
  text-align: left;
  font-weight: 600;
  color: #333;
  border-bottom: 1px solid #ddd;
}

.table-td {
  padding: 12px 15px;
  border-bottom: 1px solid #eee;
  color: #555;
}

.table-body tr:nth-child(even) {
  background-color: #f8f8f8;
}

.action-buttons {
  display: flex;
  gap: 8px;
}

.btn-edit,
.btn-delete {
  padding: 8px 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: background-color 0.3s ease;
}

.btn-edit {
  background-color: #4CAF50;
  color: white;
}

.btn-edit:hover {
  background-color: #45a049;
}

.btn-delete {
  background-color: #f44336;
  color: white;
}

.btn-delete:hover {
  background-color: #da190b;
}
</style>