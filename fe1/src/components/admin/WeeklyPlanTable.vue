<template>
    <div class="weekly-plan-list-card">
      <h2 class="weekly-plan-list-title">Danh sách Kế hoạch</h2>
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
                Hành động
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="plan in plans" :key="plan.id">
              <td class="table-td">{{ plan.name }}</td>
              <td class="table-td">{{ plan.startDate }}</td>
              <td class="table-td">{{ plan.endDate }}</td>
              <td class="table-td">{{ plan.status }}</td>
              <td class="table-td">
                <button @click="$emit('editPlan', plan)"
                  class="btn-edit">
                  Sửa
                </button>
                <button @click="$emit('deletePlan', plan.id)"
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
import { defineProps, defineEmits } from 'vue';

interface WeeklyPlan {
  id: string;
  name: string;
  startDate: string;
  endDate: string;
  status: string;
}

const props = defineProps<{
  plans: WeeklyPlan[];
}>();

const emit = defineEmits(['editPlan', 'deletePlan']);
</script>

<style scoped>
/* Thêm CSS cho component WeeklyPlanTable tại đây nếu cần */
.weekly-plan-list-card {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
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
</style>