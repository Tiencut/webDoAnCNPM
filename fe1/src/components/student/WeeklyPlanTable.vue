<template>
  <div class="weekly-plan-list-card">
    <div class="weekly-plan-management-header">
      <h1 class="weekly-plan-management-title">Kế hoạch Hàng tuần của tôi</h1>
      <input type="text" v-model="searchQuery" placeholder="Tìm kiếm kế hoạch..." class="search-input" />
      <div class="table-container">
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
              <tr v-for="plan in filteredPlans" :key="plan.id" class="cursor-pointer" @click="$emit('editPlan', plan)">
                <td class="table-td">{{ plan.name }}</td>
                <td class="table-td">{{ plan.startDate }}</td>
                <td class="table-td">{{ plan.endDate }}</td>
                <td class="table-td">{{ plan.status }}</td>
                <td class="table-td">
                  <!-- Sinh viên không có quyền xóa kế hoạch -->
                  <!-- <button @click.stop="$emit('deletePlan', plan.id)"
                    class="btn-delete">
                    Xóa
                  </button> -->
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
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
    plan.status.toLowerCase().includes(query)
  );
});
</script>

<style scoped>
.weekly-plan-list-card {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap; /* Cho phép các mục xuống dòng nếu không đủ không gian */
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

.weekly-plan-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
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
}

.add-plan-button:hover {
  background-color: #45a049;
}

.search-input {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-left: 10px;
  width: 200px;
}
</style>