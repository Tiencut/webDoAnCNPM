<template>
  <transition name="modal">
    <div v-if="show" class="modal-overlay">
      <div class="modal-box">
        <div class="flex flex-col items-center">
          <div class="icon-container">
            <svg class="icon" viewBox="0 0 24 24" fill="none">
              <circle cx="12" cy="12" r="10" fill="#3182ce" opacity="0.2"/>
              <path d="M9 12l2 2 4-4" stroke="#2563eb" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
          </div>
          <h2 class="modal-title">
            {{ isEditing ? 'Chỉnh sửa Quy tắc' : 'Thêm Quy tắc Mới' }}
          </h2>
        </div>
        <form @submit.prevent="handleSave">
          <div class="input-group">
            <label for="ruleContent" class="input-label">Nội dung Quy tắc<span class="required-star">*</span>:</label>
            <textarea
                id="ruleContent"
                v-model="editableRule.content"
                rows="5"
                class="input-textarea w-full max-w-full box-border"
                placeholder="Nhập nội dung quy tắc"
                required>
            </textarea>
          </div>
          <div class="btn-row">
            <button
              type="button"
              @click="handleClose"
              class="btn ghost"
            >
              Huỷ
            </button>
            <button
              type="submit"
              class="btn primary"
            >
              Lưu
            </button>
          </div>
        </form>
      </div>
    </div>
  </transition>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from 'vue';

interface Rule {
  id: number;
  content: string;
}

const props = defineProps<{
  show: boolean;
  rule: Rule;
  isEditing: boolean;
}>();

const emit = defineEmits(['save', 'close']);

const editableRule = ref<Rule>({ ...props.rule });

watch(() => props.rule, (newRule) => {
  editableRule.value = { ...newRule };
}, { deep: true });

const handleSave = () => {
  emit('save', editableRule.value);
};

const handleClose = () => {
  emit('close');
};
</script>

<style scoped>
.required-star{color:#e53e3e;margin-left:4px}
.modal-overlay {
  position: fixed;
  z-index: 9999;
  inset: 0;
  background: rgba(30, 41, 59, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  animation: fadeInBg .2s;
}

@keyframes fadeInBg {
  from { background: rgba(30, 41, 59, 0); }
  to { background: rgba(30, 41, 59, 0.6);}
}

.modal-box {
  background: linear-gradient(135deg, #f8fafc 65%, #e0e7ff 100%);
  border-radius: 2rem;
  padding: 2.5rem 2rem 2rem 2rem;
  width: 100%;
  max-width: 420px;
  box-shadow: 0 10px 36px 4px rgba(30,41,59,0.13);
  animation: scaleIn .2s;
}

@keyframes scaleIn {
  0% { transform: scale(0.95); opacity: 0;}
  100% { transform: scale(1); opacity: 1;}
}

.icon-container {
  background: linear-gradient(135deg, #e0e7ff, #2563eb25 90%);
  border-radius: 9999px;
  padding: 0.8rem;
  margin-bottom: 0.75rem;
}

.icon {
  width: 2.3rem;
  height: 2.3rem;
}

.modal-title {
  font-size: 1.4rem;
  font-weight: 700;
  color: #334155;
  margin-bottom: 1.5rem;
  text-align: center;
}

.input-group {
  margin-bottom: 1.6rem;
}

.input-label {
  display: block;
  font-size: 1rem;
  font-weight: 600;
  color: #4b5563;
  margin-bottom: 7px;
  letter-spacing: 0.01em;
}

.input-textarea {
  width: 100%;
  padding: 0.9rem 1.1rem;
  border: 1.8px solid #c7d2fe;
  border-radius: 1rem;
  font-size: 1rem;
  min-height: 95px;
  color: #1e293b;
  background: #f3f6fa;
  outline: none;
  resize: vertical;
  transition: border-color 0.2s, box-shadow 0.2s;
}

.input-textarea:focus {
  border-color: #4f8cff;
  box-shadow: 0 0 0 2px #b1c7ff6e;
  background: #fff;
}

.btn-row {
  display: flex;
  justify-content: flex-end;
  gap: 0.8rem;
  margin-top: 1.8rem;
}

.btn {
  font-weight: 600;
  padding: 0.65em 1.5em;
  font-size: 1rem;
  border-radius: 0.7rem;
  border: none;
  cursor: pointer;
  outline: none;
  transition: background 0.2s, color 0.2s, box-shadow 0.2s;
}

.btn.ghost {
  background: transparent;
  border: 1.6px solid #a4b4d2;
  color: #475569;
}

.btn.ghost:hover,
.btn.ghost:focus {
  border-color: #64748b;
  background: #f1f5f9;
}

.btn.primary {
  background: linear-gradient(90deg, #3b82f6 0%, #6366f1 80%);
  color: #fff;
  box-shadow: 0 4px 24px 0 #2563eb13;
}

.btn.primary:hover,
.btn.primary:focus {
  background: linear-gradient(90deg, #2563eb 0%, #4f46e5 100%);
  color: #f1f5f9;
}
</style>
