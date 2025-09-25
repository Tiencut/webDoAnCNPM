<template>
  <aside class="w-64 bg-white shadow-lg p-6 flex flex-col">
    <h2 class="text-3xl font-extrabold text-gray-800 mb-8 border-b pb-4">Điều hướng</h2>
    <nav class="flex-1">
      <ul>
        <li class="mb-4">
          <router-link to="/student-dashboard" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11v10a1 1 0 001 1h3m-3-11L12 9m2 2l-2-2m0 0l-2 2m2-2V5" />
            </svg>
            Tổng quan Sinh viên
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer-dashboard" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11v10a1 1 0 001 1h3m-3-11L12 9m2 2l-2-2m0 0l-2 2m2-2V5" />
            </svg>
            Tổng quan Giảng viên
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/profile" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
            </svg>
            Thông tin cá nhân
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/course-sections" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.253v13m0-13C10.832 5.477 9.206 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.794 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.794 5 16.5 5c1.706 0 3.332.477 4.5 1.253v13C19.832 18.477 18.206 18 16.5 18s-3.332.477-4.5 1.253" />
            </svg>
            Lớp học phần
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/groups" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M17 20h2a2 2 0 002-2V7a2 2 0 00-2-2h-2.586a1 1 0 01-.707-.293l-1.414-1.414A1 1 0 0012.586 3H7a2 2 0 00-2 2v12a2 2 0 002 2h2m0 0l-2 2m2-2l2 2m-3 0v-3m-2 3h3m-3 0h-3" />
            </svg>
            Nhóm sinh viên
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/projects" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M12 16h.01" />
            </svg>
            Đề tài/Dự án
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/reports" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M9 17v-2m3 2v-4m3 2v-6m2 10H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
            Báo cáo
          </router-link>
        </li>
        <li class="mb-4">
          <router-link to="/lecturer/notifications" class="flex items-center text-gray-700 hover:text-blue-600 font-medium py-2 px-3 rounded-lg transition duration-200 ease-in-out hover:bg-blue-50">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-3 text-blue-500" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
            </svg>
            Thông báo
          </router-link>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
/* Add any specific styles for the Sidebar here */
</style>