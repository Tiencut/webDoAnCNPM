<template>
  <div v-if="isVisible" class="modal-overlay">
    <div class="modal-content">
      <h2>Import Data</h2>
      <p>This is where your import form or content will go.</p>
      <button @click="closeModal">Close</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const isVisible = ref(false);

const openModal = () => {
  isVisible.value = true; 
};

const closeModal = () => {
  isVisible.value = false;
};

defineExpose({
  openModal,
  closeModal,
});
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 90%;
  max-width: 500px;
}
</style>