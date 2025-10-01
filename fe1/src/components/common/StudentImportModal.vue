<template>
  <div>
    <button class="import-button" @click="openModal">
      <DocumentArrowUpIcon class="h-6 w-6" />
      <span>Import Sinh viên</span>
    </button>

    <div v-if="isVisible" class="modal-overlay">
      <div class="modal-content">
        <h2>Import Sinh viên</h2>
        <p>Nội dung form import sinh viên sẽ ở đây.</p>
        <button @click="closeModal">Đóng</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { DocumentArrowUpIcon } from '@heroicons/vue/24/outline';

const isVisible = ref(false);

const openModal = () => {
  isVisible.value = true;
};

const closeModal = () => {
  isVisible.value = false;
};
</script>

<style scoped>
.import-button {
  color: white;
  text-decoration: none;
  font-size: 1.1em;
  display: flex;
  align-items: center;
  padding: 10px 15px;
  border-radius: 5px;
  transition: background-color 0.3s ease;
  background-color: #4CAF50; /* Đặt nền màu xanh lá cây */
  border: none; /* Loại bỏ viền mặc định của nút */
  cursor: pointer;
}

.import-button:hover {
  background-color: #45a049;
}

.import-button svg {
  margin-right: 10px;
  height: 24px;
  width: 24px;
}

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

button {
  margin-top: 10px;
  padding: 8px 15px;
  border: none;
  border-radius: 5px;
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}
</style>