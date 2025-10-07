<template>
  <div v-if="show" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <div class="modal-header">
        <slot name="header"></slot>
      </div>
      <button class="modal-close" @click="closeModal">&times;</button>
      <div class="modal-body">
        <slot name="body"></slot>
      </div>
      <div class="modal-footer">
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from 'vue';

const props = defineProps({
  show: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(['close']);

const closeModal = () => {
  emit('close');
};

const handleKeydown = (event: KeyboardEvent) => {
  if (event.key === 'Escape' && props.show) {
    closeModal();
  }
};

onMounted(() => {
  window.addEventListener('keydown', handleKeydown);
});

onUnmounted(() => {
  window.removeEventListener('keydown', handleKeydown);
});
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background-color: #fff;
  border-radius: 8px;
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  display: flex;
  flex-direction: column;
  gap: 10px;
  max-width: 90vw;
  box-sizing: border-box;
  flex-shrink: 1;
  word-break: break-word;
  overflow-wrap: break-word;
  z-index: 1001;
  padding: 20px; /* Thêm padding cho modal-content */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Thêm box-shadow */
}

.modal-header {
  padding: 15px 0; /* Điều chỉnh padding */
  border-bottom: 1px solid #eee;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.modal-body {
  padding: 20px 0; /* Điều chỉnh padding */
  flex-grow: 1;
  overflow-y: auto;
  max-height: calc(90vh - 120px); /* Điều chỉnh max-height để phù hợp với padding mới */
}

.modal-footer {
  padding: 15px 0; /* Điều chỉnh padding */
  border-top: 1px solid #eee;
  display: flex;
  justify-content: flex-end;
}

.modal-close {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}
</style>