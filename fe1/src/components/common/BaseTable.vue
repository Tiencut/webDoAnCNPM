<template>
  <table class="data-table">
    <thead class="table-header">
      <tr>
        <th v-if="expandable" class="table-th expand-toggle-th"></th>
        <th v-for="header in headers" :key="header.value" scope="col" class="table-th">{{ header.text }}</th>
        <th v-if="hasActions" scope="col" class="table-th">Hành động</th>
      </tr>
    </thead>
    <tbody class="table-body">
      <template v-for="item in items" :key="item[itemKey]">
        <tr :class="{ 'cursor-pointer': clickable }" @click="clickable && $emit('row-click', item)">
          <td v-if="expandable" class="table-td expand-toggle-td">
            <button @click.stop="toggleExpand(item[itemKey])" class="expand-button">
              {{ expandedRows[item[itemKey]] ? '-' : '+' }}
            </button>
          </td>
          <td v-for="header in headers" :key="header.value" class="table-td">
            <slot :name="`item-${header.value}`" :item="item">
              {{ item[header.value] }}
            </slot>
          </td>
          <td v-if="hasActions" class="table-td action-buttons">
            <slot name="actions" :item="item"></slot>
          </td>
        </tr>
        <tr v-if="expandable && expandedRows[item[itemKey]]" class="expanded-row">
          <slot name="expanded-row" :item="item"></slot>
        </tr>
      </template>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import { ref, defineProps, defineEmits, computed } from 'vue';

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
  clickable: {
    type: Boolean,
    default: false,
  },
  expandable: {
    type: Boolean,
    default: false,
  },
  allRowsExpandedByDefault: {
    type: Boolean,
    default: false,
  },
});

const emits = defineEmits(['edit', 'delete', 'row-click']);

const expandedRows = ref<{ [key: string]: boolean }>({});

// Initialize expandedRows based on allRowsExpandedByDefault prop
if (props.allRowsExpandedByDefault) {
  props.items.forEach(item => {
    expandedRows.value[item[props.itemKey]] = true;
  });
}

const toggleExpand = (key: string) => {
  expandedRows.value[key] = !expandedRows.value[key];
};
</script>

<style scoped>
/* BaseTable.vue */
.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  border-radius: 8px;
  overflow: hidden;
}

.table-header {
  background-color: #f2f2f2;
}

.table-th {
  padding: 8px;
  text-align: left;
  font-weight: bold;
  border: 1px solid #ddd;
  color: #333;
}

.table-td {
  padding: 8px;
  border: 1px solid #ddd;
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

.table-body tr.cursor-pointer:hover {
  background-color: #f0f0f0;
}

.expand-toggle-th,
.expand-toggle-td {
  width: 30px;
  text-align: center;
}

.expand-button {
  background: none;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 24px;
  height: 24px;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
}

.expanded-row td {
  padding: 0;
  border: none;
}
</style>