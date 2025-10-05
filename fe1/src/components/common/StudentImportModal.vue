<template>
  <div>
    <button class="import-button" @click="openModal">
      <DocumentArrowUpIcon class="h-6 w-6" />
      <span>Import Sinh viên</span>
    </button>

    <div v-if="isVisible" class="modal-overlay">
      <div class="modal-content">
        <h2>Import Sinh viên</h2>
        <input type="file" @change="handleFileChange" accept=".csv, .xlsx" />
        <p v-if="message" :class="{ 'text-red-500': message.includes('thất bại'), 'text-green-500': message.includes('thành công') }">{{ message }}</p>
        <button @click="importStudents" :disabled="!selectedFile">Import</button>
        <button @click="closeModal">Đóng</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { DocumentArrowUpIcon } from '@heroicons/vue/24/outline';

const isVisible = ref(false);
const selectedFile = ref<File | null>(null);
const message = ref<string>('');

const openModal = () => {
  isVisible.value = true;
  selectedFile.value = null;
  message.value = '';
};

const closeModal = () => {
  isVisible.value = false;
};

const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    selectedFile.value = target.files[0];
    message.value = '';
  } else {
    selectedFile.value = null;
    message.value = 'Vui lòng chọn một tệp để import.';
  }
};

const importStudents = async () => {
  if (!selectedFile.value) {
    message.value = 'Vui lòng chọn một tệp để import.';
    return;
  }

  message.value = 'Đang xử lý tệp...';

  // Placeholder for actual file processing and API call
  // In a real application, you would send selectedFile.value to a backend API
  // For now, we'll simulate a delay and a success/failure message.
  await new Promise(resolve => setTimeout(resolve, 2000));

  // Simulate success or failure
  const isSuccess = Math.random() > 0.5; // 50% chance of success

  if (isSuccess) {
    message.value = `Import thành công ${selectedFile.value.name}!`;
    // Optionally, you might want to close the modal or refresh the student list
    // closeModal();
  } else {
    message.value = `Import thất bại ${selectedFile.value.name}. Vui lòng thử lại.`;
  }
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
  max-width: 500px; /* Trở lại chiều rộng ban đầu */
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.modal-content h2 {
  margin-top: 0;
  color: #333;
}

.modal-content input[type="file"] {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  background-color: #f9f9f9;
}

.modal-content button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.modal-content button:not(:disabled) {
  background-color: #4CAF50;
  color: white;
}

.modal-content button:not(:disabled):hover {
  background-color: #45a049;
}

.modal-content button:disabled {
  background-color: #cccccc;
  color: #666666;
  cursor: not-allowed;
}

.modal-content p {
  color: #555;
  font-size: 0.9em;
}
.text-red-500 {
  color: #ef4444;
}

.text-green-500 {
  color: #22c55e;
}
</style>