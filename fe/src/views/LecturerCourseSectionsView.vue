<template>
  <div class="lecturer-course-sections-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Quản lý Lớp học phần</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Danh sách Lớp học phần</h2>
      <div v-if="courseSections.length === 0" class="text-gray-500">Chưa có lớp học phần nào được phân công.</div>
      <div v-else>
        <ul class="divide-y divide-gray-200">
          <li v-for="section in courseSections" :key="section.id" class="py-4 flex items-center justify-between">
            <div>
              <p class="text-lg font-semibold text-gray-900">{{ section.name }} ({{ section.code }})</p>
              <p class="text-sm text-gray-600">Số sinh viên: {{ section.students.length }}</p>
            </div>
            <div class="flex space-x-2">
              <button @click="viewSectionDetails(section.id)" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-sm">Xem chi tiết</button>
              <button @click="editSection(section.id)" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-sm">Chỉnh sửa</button>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Thêm Lớp học phần mới</h2>
      <form @submit.prevent="addCourseSection">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-4">
          <div>
            <label for="newSectionName" class="block text-gray-700 text-sm font-bold mb-2">Tên lớp học phần:</label>
            <input type="text" id="newSectionName" v-model="newSection.name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nhập tên lớp học phần">
          </div>
          <div>
            <label for="newSectionCode" class="block text-gray-700 text-sm font-bold mb-2">Mã lớp học phần:</label>
            <input type="text" id="newSectionCode" v-model="newSection.code" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nhập mã lớp học phần">
          </div>
        </div>
        <button type="submit" class="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Thêm lớp học phần</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerCourseSectionsView',
  data() {
    return {
      courseSections: [
        { id: 1, name: 'Lập trình Web', code: 'IT3001', students: [{id: 1, name: 'Sinh viên A'}, {id: 2, name: 'Sinh viên B'}] },
        { id: 2, name: 'Cấu trúc dữ liệu và giải thuật', code: 'IT2001', students: [{id: 3, name: 'Sinh viên C'}] },
      ],
      newSection: {
        name: '',
        code: '',
      },
    };
  },
  methods: {
    viewSectionDetails(id) {
      alert(`Xem chi tiết lớp học phần ${id}`);
      // Logic để điều hướng đến trang chi tiết lớp học phần
    },
    editSection(id) {
      alert(`Chỉnh sửa lớp học phần ${id}`);
      // Logic để điều hướng đến trang chỉnh sửa lớp học phần
    },
    addCourseSection() {
      if (this.newSection.name && this.newSection.code) {
        const newId = this.courseSections.length > 0 ? Math.max(...this.courseSections.map(s => s.id)) + 1 : 1;
        this.courseSections.push({ ...this.newSection, id: newId, students: [] });
        alert('Thêm lớp học phần thành công!');
        this.newSection = { name: '', code: '' };
      } else {
        alert('Vui lòng nhập đầy đủ tên và mã lớp học phần.');
      }
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>