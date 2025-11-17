<template>
  <div class="admin-report-management">
    <div class="header-card">
      <div>
        <h1 class="title">Quản lý Báo cáo</h1>
        <p class="subtitle">Danh sách báo cáo &amp; bài tập của bạn.</p>
      </div>
      <button class="add-btn" aria-label="Thêm báo cáo" @click="openAddModal">
        <span class="material-icons">add</span>
      </button>
    </div>

    <div class="reports-table-card">
      <table>
        <thead>
          <tr>
            <th>Tên Bài tập</th>
            <th>Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in mockReports" :key="item.id">
            <td>{{ item.assignmentName }}</td>
            <td>
              <div class="action-group">
                <button class="icon-btn edit" aria-label="Sửa" @click="openEditModal(item)">
                  <span class="material-icons">edit</span>
                </button>
                <button class="icon-btn delete" aria-label="Xóa" @click="handleDelete(item.id)">
                  <span class="material-icons">delete</span>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-if="showModal" class="modal-overlay">
      <div class="modal-content">
        <h3>{{ editingReport ? 'Sửa báo cáo' : 'Thêm báo cáo' }}</h3>
        <input v-model="newAssignmentName" placeholder="Tên bài tập" />
        <div class="modal-actions">
          <button @click="saveReport">{{ editingReport ? 'Lưu' : 'Thêm' }}</button>
          <button @click="showModal = false">Hủy</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

interface Report {
  id: string;
  assignmentName: string;
}

const mockReports = ref<Report[]>([
  { id: '10', assignmentName: 'Requirement Outline' },
  { id: '9', assignmentName: 'SRS v1.0' },
  { id: '2', assignmentName: 'checklist Self review SRS' },
  { id: '8', assignmentName: 'SRS v2.0' },
  { id: '7', assignmentName: 'DB Design v1.0' },
  { id: '6', assignmentName: 'DB design v2.0' },
  { id: '5', assignmentName: 'Testcase v1.0' },
  { id: '4', assignmentName: 'SRS v3.0' },
  { id: '3', assignmentName: 'Testcase v2.0' },
  { id: '1', assignmentName: 'Checklist Self review DB Design' },
]);

const showModal = ref(false);
const editingReport = ref<Report | null>(null);
const newAssignmentName = ref('');

function handleDelete(id: string) {
  if (confirm('Bạn có chắc chắn muốn xóa báo cáo này không?')) {
    mockReports.value = mockReports.value.filter(report => report.id !== id);
  }
}

function openAddModal() {
  editingReport.value = null;
  newAssignmentName.value = '';
  showModal.value = true;
}

function openEditModal(report: Report) {
  editingReport.value = report;
  newAssignmentName.value = report.assignmentName;
  showModal.value = true;
}

function saveReport() {
  if (newAssignmentName.value.trim() === '') {
    alert('Tên bài tập không được để trống');
    return;
  }
  if (editingReport.value) {
    // Sửa báo cáo
    const index = mockReports.value.findIndex(r => r.id === editingReport.value?.id);
    if (index !== -1) {
      mockReports.value[index].assignmentName = newAssignmentName.value;
    }
  } else {
    // Thêm báo cáo mới
    const newId = (Math.max(...mockReports.value.map(r => +r.id)) + 1).toString();
    mockReports.value.push({ id: newId, assignmentName: newAssignmentName.value });
  }
  showModal.value = false;
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/icon?family=Material+Icons');

.admin-report-management {
  max-width: 800px;
  margin: 2rem auto;
  padding: 0 1rem;
  font-family: 'Inter', Arial, sans-serif;
}

.header-card {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(90deg, #f3f7fa 80%, #e3ebed 100%);
  border-radius: 1.2rem;
  box-shadow: 0 3px 16px 0 rgba(120,140,160,0.08);
  margin-bottom: 2rem;
  padding: 2rem 2rem 1.5rem 2rem;
  position: relative;
}

.title {
  font-size: 2.2rem;
  font-weight: 700;
  color: #2e425e;
  margin-bottom: 0.4rem;
}
.subtitle {
  font-size: 1rem;
  color: #65748b;
  margin-bottom: 0;
}

.add-btn {
  background: #475be8;
  color: #fff;
  border: none;
  border-radius: 50%;
  width: 48px;
  height: 48px;
  box-shadow: 0 4px 16px 0 rgba(71,91,232,0.12);
  font-size: 2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background 0.2s;
}
.add-btn:hover {
  background: #274db5;
}

.reports-table-card {
  background: #fff;
  border-radius: 1rem;
  box-shadow: 0 2px 12px 0 rgba(40,70,150,0.09);
  padding: 1.5rem 2rem;
  overflow-x: auto;
}

.reports-table-card table {
  width: 100%;
  border-collapse: collapse;
}
.reports-table-card thead tr {
  background: #f7fafc;
}
.reports-table-card th, .reports-table-card td {
  padding: 1rem;
  text-align: left;
  color: #384770;
  font-size: 1rem;
}
.reports-table-card tbody tr {
  transition: background 0.13s;
}
.reports-table-card tbody tr:hover {
  background: #f1f7fa;
}

.action-group {
  display: flex;
  gap: 0.5rem;
}
.icon-btn {
  background: #eef0f6;
  border: none;
  border-radius: 8px;
  padding: 6px;
  cursor: pointer;
  color: #475be8;
  transition: background 0.2s, color 0.2s;
  box-shadow: 0 1px 4px 0 rgba(71,91,232,0.04);
  display: flex;
  align-items: center;
  font-size: 1.3rem;
}
.icon-btn.edit:hover {
  background: #e3ebed;
  color: #1e68d7;
}
.icon-btn.delete {
  color: #d14637;
}
.icon-btn.delete:hover {
  background: #fae6e2;
  color: #aa2915;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}
.modal-content {
  background: white;
  border-radius: 10px;
  padding: 2rem;
  width: 320px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.2);
}
.modal-content h3 {
  margin-bottom: 1rem;
  color: #2e425e;
}
.modal-content input {
  width: 100%;
  padding: 0.6rem;
  margin-bottom: 1rem;
  font-size: 1rem;
  border-radius: 6px;
  border: 1px solid #ccc;
}
.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}
.modal-actions button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
.modal-actions button:first-child {
  background-color: #475be8;
  color: white;
}
.modal-actions button:last-child {
  background-color: #eee;
  color: #384770;
}
</style>
