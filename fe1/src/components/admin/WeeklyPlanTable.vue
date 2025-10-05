<template>
    <div class="weekly-plan-list-card">
<div class="header-controls">
      <h1 class="weekly-plan-management-title">Quản lý Kế hoạch Làm việc Hàng tuần</h1>
      <button @click="$emit('addPlan')" class="btn btn-primary add-plan-button">Thêm Kế hoạch Mới</button>
      <input type="text" v-model="searchQuery" placeholder="Tìm kiếm kế hoạch..." class="search-input" />
    </div>
      <div class="table-responsive">
        <table class="data-table">
          <thead>
            <tr>
              <th class="table-th">
                Tên Kế hoạch
              </th>
              <th class="table-th">
                Ngày Bắt đầu
              </th>
              <th class="table-th">
                Ngày Kết thúc
              </th>
              <th class="table-th">
                Trạng thái
              </th>
              <th class="table-th">
                Thành viên
              </th>
              <th class="table-th">
                Hành động
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="plan in filteredPlans" :key="plan.id" class="cursor-pointer" @click="$emit('editPlan', plan)">
              <td class="table-td">{{ plan.name }}</td>
              <td class="table-td">{{ plan.startDate }}</td>
              <td class="table-td">{{ plan.endDate }}</td>
              <td class="table-td">{{ plan.status }}</td>
              <td class="table-td">{{ plan.members.join(', ') }}</td>
              <td class="table-td">
                <button @click.stop="$emit('deletePlan', plan.id)"
                  class="btn-delete">
                  Xóa
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits, ref, computed } from 'vue';

interface WeeklyPlan {
  id: string;
  name: string;
  startDate: string;
  endDate: string;
  status: string;
  members: string[];
}

const props = defineProps<{
  plans: WeeklyPlan[];
}>();

const emit = defineEmits(['editPlan', 'deletePlan', 'addPlan']);

const searchQuery = ref('');

const filteredPlans = computed(() => {
  if (!searchQuery.value) {
    return props.plans;
  }
  const query = searchQuery.value.toLowerCase();
  return props.plans.filter(plan =>
    plan.name.toLowerCase().includes(query) ||
    plan.startDate.toLowerCase().includes(query) ||
    plan.endDate.toLowerCase().includes(query) ||
    plan.status.toLowerCase().includes(query) ||
    plan.members.some(member => member.toLowerCase().includes(query))
  );
});
</script>

<style scoped>
/* Thêm CSS cho component WeeklyPlanTable tại đây nếu cần */
.weekly-plan-list-card {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.weekly-plan-list-title {
  font-size: 1.5rem;
  color: #333;
  margin-bottom: 15px;
}

.table-responsive {
  overflow-x: auto;
}

.data-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.data-table th,
.data-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.data-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.btn-edit,
.btn-delete {
  padding: 6px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9rem;
  margin-right: 5px;
}

.btn-edit {
  background-color: #4CAF50;
  color: white;
}

.btn-delete {
  background-color: #f44336;
  color: white;
}

.header-controls {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1rem;
  }

  .weekly-plan-management-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 0;
  }

.add-plan-button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  margin-bottom: 20px;
  margin-left: 1rem;
}

.add-plan-button:hover {
  background-color: #45a049;
}

.search-input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-left: 1rem;
  width: 200px;
}
</style>