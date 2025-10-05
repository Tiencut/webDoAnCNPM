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
        :has-actions="true"
        :clickable="true"
        @row-click="editStudent"
        @delete="deleteStudent"
      >
        <template #actions="{ item }">
          <button @click.stop="deleteStudent(item.id)" class="btn-danger">
            Xóa
          </button>
        </template>
      </BaseTable>
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
  studentCode: string;
  fullName: string;
  university: string;
  major: string;
  gpa: number;
  gender: string;
  dob: string;
  email: string;
  phone: string;
  graduationYear: number;
  course: string;
  idNumber: string;
}

const studentTableHeaders = [
  { text: 'Mã SV', value: 'studentCode' },
  { text: 'Họ và Tên', value: 'fullName' },
  { text: 'Trường', value: 'university' },
  { text: 'Chuyên ngành', value: 'major' },
  { text: 'GPA', value: 'gpa' },
  { text: 'Giới tính', value: 'gender' },
  { text: 'Ngày sinh', value: 'dob' },
  { text: 'Email cá nhân', value: 'email' },
  { text: 'Số điện thoại', value: 'phone' },
  { text: 'Năm tốt nghiệp', value: 'graduationYear' },
  { text: 'Khóa', value: 'course' },
  { text: 'CMND/CCCD', value: 'idNumber' },
];

const students = ref<Student[]>([
  { id: 1, studentCode: 'SV001', fullName: 'Nguyễn Văn A', university: 'Đại học ABC', major: 'Công nghệ thông tin', gpa: 3.5, gender: 'Nam', dob: '2000-01-01', email: 'vana@example.com', phone: '0901234567', graduationYear: 2022, course: 'K44', idNumber: '123456789' },
  { id: 2, studentCode: 'SV002', fullName: 'Trần Thị B', university: 'Đại học XYZ', major: 'Khoa học Máy tính', gpa: 3.8, gender: 'Nữ', dob: '2001-02-02', email: 'thib@example.com', phone: '0907654321', graduationYear: 2023, course: 'K45', idNumber: '987654321' },
]);

const currentStudent = ref<Student>({ id: 0, studentCode: '', fullName: '', university: '', major: '', gpa: 0, gender: '', dob: '', email: '', phone: '', graduationYear: 0, course: '', idNumber: '' });
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
  currentStudent.value = { id: 0, studentCode: '', fullName: '', university: '', major: '', gpa: 0, gender: '', dob: '', email: '', phone: '', graduationYear: 0, course: '', idNumber: '' };
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