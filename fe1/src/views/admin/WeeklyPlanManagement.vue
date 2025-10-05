<template>
  <div class="weekly-plan-management-container">
    <WeeklyPlanForm :initialPlan="currentPlan" :isEditingProp="isEditing" :show="showWeeklyPlanFormModal" @save="handleSavePlan" @cancel="handleCancelEdit" @close="showWeeklyPlanFormModal = false" />

    <WeeklyPlanTable :plans="plans" @editPlan="editPlan" @deletePlan="deletePlan" @add-plan="showAddPlanModal" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import WeeklyPlanForm from '../../components/WeeklyPlanForm.vue';
import WeeklyPlanTable from '../../components/admin/WeeklyPlanTable.vue';

interface WeeklyPlan {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  status: 'pending' | 'in_progress' | 'completed';
}

export default defineComponent({
  name: 'WeeklyPlanManagement',
  components: {
    WeeklyPlanForm,
    WeeklyPlanTable,
  },
  setup() {
    const plans = ref<WeeklyPlan[]>([
      { id: 1, name: 'Kế hoạch tuần 1', startDate: '2023-01-01', endDate: '2023-01-07', status: 'completed' },
      { id: 2, name: 'Kế hoạch tuần 2', startDate: '2023-01-08', endDate: '2023-01-14', status: 'in_progress' },
    ]);

    const currentPlan = reactive<WeeklyPlan>({
      id: 0,
      name: '',
      startDate: '',
      endDate: '',
      status: 'pending',
    });

    const isEditing = ref(false);
    const showWeeklyPlanFormModal = ref(false);

    const showAddPlanModal = () => {
      resetForm();
      isEditing.value = false;
      showWeeklyPlanFormModal.value = true;
    };

    const handleSavePlan = (plan: WeeklyPlan) => {
      if (isEditing.value) {
        const index = plans.value.findIndex(p => p.id === plan.id);
        if (index !== -1) {
          plans.value[index] = { ...plan };
        }
      } else {
        plan.id = plans.value.length > 0 ? Math.max(...plans.value.map(p => p.id)) + 1 : 1;
        plans.value.push({ ...plan });
      }
      showWeeklyPlanFormModal.value = false;
      resetForm();
    };

    const handleCancelEdit = () => {
      showWeeklyPlanFormModal.value = false;
      resetForm();
    };

    const editPlan = (plan: WeeklyPlan) => {
      Object.assign(currentPlan, plan);
      isEditing.value = true;
      showWeeklyPlanFormModal.value = true;
    };

    const deletePlan = (id: number) => {
      if (confirm(`Bạn có chắc chắn muốn xóa kế hoạch này không?`)) {
        plans.value = plans.value.filter(p => p.id !== id);
      }
    };

    const resetForm = () => {
      currentPlan.id = 0;
      currentPlan.name = '';
      currentPlan.startDate = '';
      currentPlan.endDate = '';
      currentPlan.status = 'pending';
      isEditing.value = false;
    };

    return {
      plans,
      currentPlan,
      isEditing,
      handleSavePlan,
      handleCancelEdit,
      editPlan,
      deletePlan,
      showWeeklyPlanFormModal,
      showAddPlanModal,
    };
  },
});
</script>

<style scoped>
.weekly-plan-management-container {
  max-width: 1200px;
  margin: 0 auto;
}

.weekly-plan-list-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
}

.weekly-plan-list-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.table-responsive {
  overflow-x: auto;
}

.data-table {
  min-width: 100%;
  background-color: white;
  border-collapse: collapse;
}

.data-table thead tr {
  border-bottom: 1px solid #e2e8f0;
}

.table-th {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
  background-color: #f7fafc;
  text-align: left;
  font-size: 0.75rem;
  font-weight: 600;
  color: #4a5568;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.table-td {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
}

.btn-edit {
  background-color: #ecc94b;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
  margin-right: 0.5rem;
}

.btn-edit:hover {
  background-color: #d69e2e;
}

.btn-delete {
  background-color: #ef4444;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
}

.btn-delete:hover {
  background-color: #dc2626;
}
</style>