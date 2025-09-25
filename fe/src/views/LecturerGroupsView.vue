<template>
  <div class="lecturer-groups-container min-h-screen bg-gray-100 p-6">
    <h1 class="text-4xl font-bold text-gray-800 mb-8">Quản lý Nhóm sinh viên</h1>

    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Danh sách Nhóm</h2>
      <div v-if="groups.length === 0" class="text-gray-500">Chưa có nhóm sinh viên nào.</div>
      <div v-else>
        <ul class="divide-y divide-gray-200">
          <li v-for="group in groups" :key="group.id" class="py-4 flex items-center justify-between">
            <div>
              <p class="text-lg font-semibold text-gray-900">{{ group.name }}</p>
              <p class="text-sm text-gray-600">Số thành viên: {{ group.members.length }}</p>
              <p class="text-sm text-gray-600">Đề tài: {{ group.project || 'Chưa có' }}</p>
            </div>
            <div class="flex space-x-2">
              <button @click="viewGroupDetails(group.id)" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded text-sm">Xem chi tiết</button>
              <button @click="assignProject(group.id)" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded text-sm">Phân công đề tài</button>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl font-semibold text-gray-700 mb-4">Tạo Nhóm mới</h2>
      <form @submit.prevent="createGroup">
        <div class="mb-4">
          <label for="newGroupName" class="block text-gray-700 text-sm font-bold mb-2">Tên nhóm:</label>
          <input type="text" id="newGroupName" v-model="newGroup.name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" placeholder="Nhập tên nhóm">
        </div>
        <button type="submit" class="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Tạo nhóm</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LecturerGroupsView',
  data() {
    return {
      groups: [
        { id: 1, name: 'Nhóm 1', members: ['SV A', 'SV B'], project: 'Phát triển ứng dụng quản lý sinh viên' },
        { id: 2, name: 'Nhóm 2', members: ['SV C', 'SV D'], project: 'Nghiên cứu về AI trong giáo dục' },
      ],
      newGroup: {
        name: '',
      },
    };
  },
  methods: {
    viewGroupDetails(id) {
      alert(`Xem chi tiết nhóm ${id}`);
      // Logic để điều hướng đến trang chi tiết nhóm
    },
    assignProject(id) {
      alert(`Phân công đề tài cho nhóm ${id}`);
      // Logic để phân công đề tài cho nhóm
    },
    createGroup() {
      if (this.newGroup.name) {
        const newId = this.groups.length > 0 ? Math.max(...this.groups.map(g => g.id)) + 1 : 1;
        this.groups.push({ ...this.newGroup, id: newId, members: [], project: null });
        alert('Tạo nhóm thành công!');
        this.newGroup.name = '';
      } else {
        alert('Vui lòng nhập tên nhóm.');
      }
    },
  },
};
</script>

<style scoped>
/* Các style tùy chỉnh nếu cần */
</style>