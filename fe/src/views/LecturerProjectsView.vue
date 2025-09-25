<template>
  <div class="lecturer-projects-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Quản lý Đề tài/Dự án</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Danh sách Đề tài</h2>
      <div v-if="projects.length === 0" class="text-gray-500">Chưa có đề tài nào được phân công.</div>
      <div v-else>
        <ul class="divide-y divide-gray-200">
          <li v-for="project in projects" :key="project.id" class="py-4 flex items-center justify-between">
            <div>
              <p class="text-lg font-semibold text-gray-900">{{ project.name }}</p>
              <p class="text-sm text-gray-600">Nhóm: {{ project.group }}</p>
              <p class="text-sm text-gray-600">Trạng thái: <span :class="getStatusClass(project.status)">{{ project.status }}</span></p>
            </div>
            <div class="flex space-x-2">
              <button @click="viewProjectDetails(project.id)" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-sm">Xem chi tiết</button>
              <button @click="evaluateProject(project.id)" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-sm">Đánh giá</button>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Phân công Đề tài mới</h2>
      <form @submit.prevent="assignNewProject">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mb-4">
          <div>
            <label for="newProjectName" class="block text-gray-700 text-sm font-bold mb-2">Tên đề tài:</label>
            <input type="text" id="newProjectName" v-model="newProject.name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nhập tên đề tài">
          </div>
          <div>
            <label for="newProjectGroup" class="block text-gray-700 text-sm font-bold mb-2">Nhóm sinh viên:</label>
            <select id="newProjectGroup" v-model="newProject.group" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
              <option value="">Chọn nhóm</option>
              <option v-for="group in availableGroups" :key="group.id" :value="group.name">{{ group.name }}</option>
            </select>
          </div>
        </div>
        <button type="submit" class="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Phân công đề tài</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerProjectsView',
  data() {
    return {
      projects: [
        { id: 1, name: 'Phát triển ứng dụng quản lý sinh viên', group: 'Nhóm 1', status: 'Đang thực hiện' },
        { id: 2, name: 'Nghiên cứu về AI trong giáo dục', group: 'Nhóm 2', status: 'Đã hoàn thành' },
      ],
      availableGroups: [
        { id: 1, name: 'Nhóm 1' },
        { id: 2, name: 'Nhóm 2' },
        { id: 3, name: 'Nhóm 3' },
      ],
      newProject: {
        name: '',
        group: '',
      },
    };
  },
  methods: {
    getStatusClass(status) {
      return {
        'text-yellow-600': status === 'Đang thực hiện',
        'text-green-600': status === 'Đã hoàn thành',
        'text-red-600': status === 'Bị hủy',
      };
    },
    viewProjectDetails(id) {
      alert(`Xem chi tiết đề tài ${id}`);
      // Logic để điều hướng đến trang chi tiết đề tài
    },
    evaluateProject(id) {
      alert(`Đánh giá đề tài ${id}`);
      // Logic để điều hướng đến trang đánh giá đề tài
    },
    assignNewProject() {
      if (this.newProject.name && this.newProject.group) {
        const newId = this.projects.length > 0 ? Math.max(...this.projects.map(p => p.id)) + 1 : 1;
        this.projects.push({ ...this.newProject, id: newId, status: 'Đang thực hiện' });
        alert('Phân công đề tài thành công!');
        this.newProject = { name: '', group: '' };
      } else {
        alert('Vui lòng nhập đầy đủ tên đề tài và chọn nhóm.');
      }
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>