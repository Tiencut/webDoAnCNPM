<template>
  <div class="task-management-view">
    <!-- Nộp báo cáo -->
    <ReportSubmissionForm :assignments="mockAssignments" />

  </div>
</template>

<script setup lang="ts">
import TaskManagement from '../../components/task/TaskManagement.vue';
import { ref } from 'vue';
import ReportSubmissionForm from '@/components/submission/ReportSubmissionForm.vue';


const currentUserGroupId = ref(null);
const userRole = ref('');
const currentUserName = ref('');
</script>

<style scoped>
.task-management-view {
  padding: 15px;
}
</style>