<template>
  <Modal :show="show" @close="cancelEdit">
    <div class="weekly-plan-form-card">
      <h2 class="weekly-plan-form-title">{{ isEditing ? 'Chỉnh sửa Kế hoạch' : 'Thêm Kế hoạch Mới' }}</h2>
      <form @submit.prevent="savePlan">
        <div class="form-grid">
          <div class="form-group">
            <label for="planName" class="form-label">Tên Kế hoạch:</label>
            <input type="text" id="planName" v-model="currentPlan.name"
                   class="form-input"
                   required>
          </div>
          <div class="form-group">
            <label for="startDate" class="form-label">Ngày Bắt đầu:</label>
            <input type="date" id="startDate" v-model="currentPlan.startDate"
                   class="form-input"
                   required>
          </div>
          <div class="form-group">
            <label for="endDate" class="form-label">Ngày Kết thúc:</label>
            <input type="date" id="endDate" v-model="currentPlan.endDate"
                   class="form-input"
                   required>
          </div>
          <div class="form-group">
            <label for="status" class="form-label">Trạng thái:</label>
            <select id="status" v-model="currentPlan.status"
                    class="form-select"
                    required>
              <option value="pending">Chưa bắt đầu</option>
              <option value="in_progress">Đang tiến hành</option>
              <!-- Sinh viên không thể tự ý đặt trạng thái là Hoàn thành -->
              <!-- <option value="completed">Hoàn thành</option> -->
            </select>
          </div>
        </div>
        <div class="form-actions">
          <button type="submit"
                  class="btn btn-primary">
            {{ isEditing ? 'Cập nhật' : 'Thêm Kế hoạch' }}
          </button>
          <button type="button" @click="cancelEdit" v-if="isEditing"
                  class="btn btn-secondary">
            Hủy
          </button>
        </div>
      </form>
    </div>
  </Modal>
</template>

<script lang="ts">
import { defineComponent, ref, reactive, watch } from 'vue';
import Modal from '../common/Modal.vue';

interface WeeklyPlan {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  status: 'pending' | 'in_progress' | 'completed';
}

export default defineComponent({
  name: 'WeeklyPlanForm',
  components: {
    Modal,
  },
  props: {
    initialPlan: {
      type: Object as () => WeeklyPlan,
      default: () => ({ id: 0, name: '', startDate: '', endDate: '', status: 'pending' }),
    },
    isEditingProp: {
      type: Boolean,
      default: false,
    },
    show: {
      type: Boolean,
      default: false,
    },
  },
  emits: ['save', 'close'],
  setup(props, { emit }) {
    const currentPlan = reactive<WeeklyPlan>({ ...props.initialPlan });
    const isEditing = ref(props.isEditingProp);

    watch(() => props.initialPlan, (newVal) => {
      Object.assign(currentPlan, newVal);
    });

    watch(() => props.isEditingProp, (newVal) => {
      isEditing.value = newVal;
    });

    const resetForm = () => {
      Object.assign(currentPlan, { id: 0, name: '', startDate: '', endDate: '', status: 'pending' });
      isEditing.value = false;
    };

    const savePlan = () => {
      emit('save', { ...currentPlan });
      emit('close');
      resetForm();
    };

    const cancelEdit = () => {
      emit('close');
      resetForm();
    };

    return {
      currentPlan,
      isEditing,
      savePlan,
      cancelEdit,
    };
  },
});
</script>

<style scoped>
.weekly-plan-form-card {
  background-color: white;
  box-shadow: none;
  border-radius: 0;
  padding: 0;
  margin-bottom: 0;
}

.weekly-plan-form-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1rem;
  margin-bottom: 1rem;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

.form-group {
  margin-bottom: 1rem;
}

.form-label {
  display: block;
  color: #4a5568;
  font-size: 0.875rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
}

.form-input,
.form-select {
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
  appearance: none;
  border: 1px solid #e2e8f0;
  border-radius: 0.25rem;
  width: 100%;
  padding: 0.5rem 0.75rem;
  color: #4a5568;
  line-height: 1.25;
}

.form-input:focus,
.form-select:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5);
}

.form-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.btn {
  font-weight: bold;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  outline: none;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.btn-primary {
  background-color: #4299e1;
  color: white;
}

.btn-primary:hover {
  background-color: #2b6cb0;
}

.btn-secondary {
  background-color: #a0aec0;
  color: white;
}

.btn-secondary:hover {
  background-color: #718096;
}
</style>