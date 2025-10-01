<template>
  <div class="student-management-content-container">
    <div class="student-list-card">
      <div class="student-list-header">
        <h2 class="student-list-title">Danh sách Sinh viên</h2>
        <StudentImportModal/>
        <button @click="toggleForm" class="btn-add-student">
          {{ showForm ? 'Ẩn Form' : 'Thêm Sinh viên Mới' }}
        </button>
      </div>

      <Modal :show="showForm" @close="cancelEdit">
        <StudentForm
          :student="currentStudent"
          :isEditing="isEditing"
          @save="handleSaveStudent"
          @cancel="cancelEdit"
        />
      </Modal>

      <BaseTable
        :headers="studentTableHeaders"
        :items="students"
        item-key="id"
        @edit="editStudent"
        @delete="deleteStudent"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import StudentForm from './StudentForm.vue';
import Modal from '../common/Modal.vue';
import BaseTable from '../common/BaseTable.vue';
import StudentImportModal from '../common/StudentImportModal.vue';

interface Student {
  id: number;
  name: string;
  studentId: string;
  email: string;
  major: string;
}

const studentTableHeaders = [
  { text: 'Tên Sinh viên', value: 'name' },
  { text: 'Mã Sinh viên', value: 'studentId' },
  { text: 'Email', value: 'email' },
  { text: 'Chuyên ngành', value: 'major' },
];

const students = ref<Student[]>([
  { id: 1, name: 'Nguyễn Văn A', studentId: 'SV001', email: 'vana@example.com', major: 'Công nghệ thông tin' },
  { id: 2, name: 'Trần Thị B', studentId: 'SV002', email: 'thib@example.com', major: 'Khoa học Máy tính' },
]);

const currentStudent = ref<Student>({ id: 0, name: '', studentId: '', email: '', major: '' });
const isEditing = ref(false);
const showForm = ref(false);

const handleSaveStudent = (student: Student) => {
  if (isEditing.value) {
    const index = students.value.findIndex(s => s.id === student.id);
    if (index !== -1) {
      students.value[index] = { ...student };
    }
  } else {
    student.id = students.value.length > 0 ? Math.max(...students.value.map(s => s.id)) + 1 : 1;
    students.value.push({ ...student });
  }
  cancelEdit();
};

const editStudent = (student: Student) => {
  currentStudent.value = { ...student };
  isEditing.value = true;
  showForm.value = true;
};

const deleteStudent = (id: number) => {
  if (confirm('Bạn có chắc chắn muốn xóa sinh viên này không?')) {
    students.value = students.value.filter(s => s.id !== id);
  }
};

const toggleForm = () => {
  showForm.value = !showForm.value;
  if (!showForm.value) {
    resetForm();
  }
};

const cancelEdit = () => {
  resetForm();
  showForm.value = false;
};

const resetForm = () => {
  currentStudent.value = { id: 0, name: '', studentId: '', email: '', major: '' };
  isEditing.value = false;
};
</script>

<style scoped>
.student-management-content-container {
  padding: 1rem; /* p-4 */
}

.student-list-card {
  background-color: #fff; /* bg-white */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
  border-radius: 0.5rem; /* rounded-lg */
  padding: 1rem; /* p-4 */
}

.student-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem; /* Khoảng cách giữa header và bảng */
}

.student-list-title {
  font-size: 1.25rem; /* text-xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 0; /* Đặt lại margin-bottom */
}

.btn-add-student {
  background-color: #2563eb; /* Blue-600 */
  color: #fff; /* White */
  font-weight: 700; /* Bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.375rem; /* rounded-md */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
  transition: background-color 0.3s ease-in-out;
  margin-bottom: 0; /* Đặt lại margin-bottom */
}

.btn-add-student:hover {
  background-color: #1d4ed8; /* Blue-700 */
}


</style>