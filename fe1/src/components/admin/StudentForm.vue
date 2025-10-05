<template>
  <div class="student-form-card">
    <h2 class="student-form-title">{{ isEditing ? 'Chỉnh sửa Sinh viên' : 'Thêm Sinh viên mới' }}</h2>
    <form @submit.prevent="saveStudent">
      <div class="form-grid">
        <div>
          <label for="studentCode" class="form-label">Mã Sinh viên:</label>
          <input type="text" id="studentCode" v-model="currentStudent.studentCode" class="form-input" required>
        </div>
        <div>
          <label for="fullName" class="form-label">Họ và Tên:</label>
          <input type="text" id="fullName" v-model="currentStudent.fullName" class="form-input" required>
        </div>
        <div>
          <label for="university" class="form-label">Trường:</label>
          <input type="text" id="university" v-model="currentStudent.university" class="form-input" required>
        </div>
        <div>
          <label for="major" class="form-label">Chuyên ngành:</label>
          <input type="text" id="major" v-model="currentStudent.major" class="form-input" required>
        </div>
        <div>
          <label for="gpa" class="form-label">GPA:</label>
          <input type="number" id="gpa" v-model.number="currentStudent.gpa" class="form-input" step="0.1" required>
        </div>
        <div>
          <label for="gender" class="form-label">Giới tính:</label>
          <select id="gender" v-model="currentStudent.gender" class="form-input" required>
            <option value="">Chọn giới tính</option>
            <option value="Nam">Nam</option>
            <option value="Nữ">Nữ</option>
            <option value="Khác">Khác</option>
          </select>
        </div>
        <div>
          <label for="dob" class="form-label">Ngày sinh:</label>
          <input type="date" id="dob" v-model="currentStudent.dob" class="form-input" required>
        </div>
        <div>
          <label for="email" class="form-label">Email cá nhân:</label>
          <input type="email" id="email" v-model="currentStudent.email" class="form-input" required>
        </div>
        <div>
          <label for="phone" class="form-label">Số điện thoại:</label>
          <input type="tel" id="phone" v-model="currentStudent.phone" class="form-input" required>
        </div>
        <div>
          <label for="graduationYear" class="form-label">Năm tốt nghiệp:</label>
          <input type="number" id="graduationYear" v-model.number="currentStudent.graduationYear" class="form-input" required>
        </div>
        <div>
          <label for="course" class="form-label">Khóa:</label>
          <input type="text" id="course" v-model="currentStudent.course" class="form-input" required>
        </div>
        <div>
          <label for="idNumber" class="form-label">CMND/CCCD:</label>
          <input type="text" id="idNumber" v-model="currentStudent.idNumber" class="form-input" required>
        </div>
      </div>
      <div class="form-actions">
        <button type="submit" class="btn-primary">
          {{ isEditing ? 'Cập nhật' : 'Thêm Sinh viên' }}
        </button>
        <button type="button" @click="cancelEdit" v-if="isEditing" class="btn-secondary">
          Hủy
        </button>
      </div>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';

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

const props = defineProps<{
  student: Student;
  isEditing: boolean;
}>();

const emit = defineEmits(['save', 'cancel']);

const currentStudent = ref<Student>({ ...props.student });

watch(() => props.student, (newVal) => {
  currentStudent.value = { ...newVal };
});

const saveStudent = () => {
  emit('save', currentStudent.value);
};

const cancelEdit = () => {
  emit('cancel');
};
</script>

<style scoped>
.student-form-card {
  background-color: #fff; /* bg-white */
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* shadow-md */
  border-radius: 0.5rem; /* rounded-lg */
  padding: 1rem; /* p-4 */
  max-width: 100%;
}

.student-form-title {
  font-size: 1.25rem; /* text-xl */
  font-weight: 600; /* font-semibold */
  margin-bottom: 0.75rem; /* mb-3 */
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr; /* grid-cols-1 */
  gap: 1rem; /* gap-4 */
  margin-bottom: 1rem; /* mb-4 */
}

.form-grid > div {
  width: 100%;
  min-width: 0;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: 1fr 1fr; /* md:grid-cols-2 */
  }
}

.form-label {
  display: block;
  color: #374151; /* text-gray-700 */
  font-size: 0.875rem; /* text-sm */
  font-weight: 700; /* font-bold */
  margin-bottom: 0.5rem; /* mb-2 */
  word-break: break-word;
}

.form-input {
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06); /* shadow */
  appearance: none;
  border: 1px solid #d1d5db; /* border */
  border-radius: 0.25rem; /* rounded */
  width: 100%; /* w-full */
  padding: 0.5rem 0.75rem; /* py-2 px-3 */
  color: #374151; /* text-gray-700 */
  line-height: 1.25; /* leading-tight */
  overflow-wrap: break-word;
  box-sizing: border-box;
  min-width: 0;
  flex-shrink: 1;
}

.form-input:focus {
  outline: none; /* focus:outline-none */
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5); /* focus:shadow-outline */
}

.form-actions {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.btn-primary {
  background-color: #3b82f6; /* bg-blue-500 */
  color: #fff; /* text-white */
  font-weight: 700; /* font-bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.25rem; /* rounded */
  transition: background-color 0.2s ease-in-out;
}

.btn-primary:hover {
  background-color: #2563eb; /* hover:bg-blue-700 */
}

.btn-primary:focus {
  outline: none; /* focus:outline-none */
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.5); /* focus:shadow-outline */
}

.btn-secondary {
  background-color: #6b7280; /* bg-gray-500 */
  color: #fff; /* text-white */
  font-weight: 700; /* font-bold */
  padding: 0.5rem 1rem; /* py-2 px-4 */
  border-radius: 0.25rem; /* rounded */
  transition: background-color 0.2s ease-in-out;
}

.btn-secondary:hover {
  background-color: #4b5563; /* hover:bg-gray-700 */
}

.btn-secondary:focus {
  outline: none; /* focus:outline-none */
  box-shadow: 0 0 0 3px rgba(107, 114, 128, 0.5); /* focus:shadow-outline */
}
</style>