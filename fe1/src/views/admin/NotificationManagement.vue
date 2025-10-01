<template>
  <div class="notification-management-container">
    <h1 class="notification-management-title">Quản lý Thông báo</h1>

    <button @click="showAddNotificationModal" class="btn btn-primary add-notification-btn">
      Thêm Thông báo Mới
    </button>

    <!-- Form thêm/chỉnh sửa thông báo dưới dạng modal -->
    <NotificationForm :initialNotification="currentNotification" :isEditingProp="isEditing" :show="showNotificationFormModal"
      @save="handleSaveNotification" @cancel="handleCancelEdit" @close="showNotificationFormModal = false" />

    <!-- Bảng danh sách thông báo -->
    <div class="notification-list-card">
      <h2 class="notification-list-title">Danh sách Thông báo</h2>
      <div class="table-responsive">
        <table class="data-table">
          <thead>
            <tr>
              <th class="table-th">
                Tiêu đề
              </th>
              <th class="table-th">
                Nội dung
              </th>
              <th class="table-th">
                Ngày
              </th>
              <th class="table-th">
                Hành động
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="notification in notifications" :key="notification.id">
              <td class="table-td">{{ notification.title }}</td>
              <td class="table-td">{{ notification.message }}</td>
              <td class="table-td">
                {{ notification.date }}
              </td>
              <td class="table-td">
                <button @click="editNotification(notification)"
                  class="btn-edit">
                  Sửa
                </button>
                <button @click="deleteNotification(notification.id)"
                  class="btn-delete">
                  Xóa
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import NotificationForm from '../../components/admin/NotificationForm.vue';

interface Notification {
  id: number;
  title: string;
  content: string;
  targetRole: 'all' | 'student' | 'lecturer' | 'admin';
  createdAt: string;
}

export default defineComponent({
  name: 'NotificationManagement',
  components: {
    NotificationForm,
  },
  setup() {
    const notifications = ref<Notification[]>([
      { id: 1, title: 'Thông báo 1', content: 'Nội dung thông báo 1', targetRole: 'all', createdAt: '2023-01-01' },
      { id: 2, title: 'Thông báo 2', content: 'Nội dung thông báo 2', targetRole: 'student', createdAt: '2023-01-02' },
    ]);

    const currentNotification = reactive<Notification>({
      id: 0,
      title: '',
      content: '',
      targetRole: 'all',
      createdAt: '',
    });

    const isEditing = ref(false);
    const showNotificationFormModal = ref(false);

    const showAddNotificationModal = () => {
      resetForm();
      isEditing.value = false;
      showNotificationFormModal.value = true;
    };

    const handleSaveNotification = (notification: Notification) => {
      if (isEditing.value) {
        const index = notifications.value.findIndex(n => n.id === notification.id);
        if (index !== -1) {
          notifications.value[index] = { ...notification };
        }
      } else {
        notification.id = notifications.value.length > 0 ? Math.max(...notifications.value.map(n => n.id)) + 1 : 1;
        notifications.value.push({ ...notification });
      }
      showNotificationFormModal.value = false;
      resetForm();
    };

    const handleCancelEdit = () => {
      showNotificationFormModal.value = false;
      resetForm();
    };

    const editNotification = (notification: Notification) => {
      Object.assign(currentNotification, notification);
      isEditing.value = true;
      showNotificationFormModal.value = true;
    };

    const deleteNotification = (id: number) => {
      notifications.value = notifications.value.filter(n => n.id !== id);
    };

    const resetForm = () => {
      currentNotification.id = 0;
      currentNotification.title = '';
      currentNotification.content = '';
      currentNotification.targetRole = 'all';
      currentNotification.createdAt = '';
      isEditing.value = false;
    };

    return {
      notifications,
      currentNotification,
      isEditing,
      showNotificationFormModal,
      showAddNotificationModal,
      handleSaveNotification,
      handleCancelEdit,
      editNotification,
      deleteNotification,
    };
  },
});
</script>

<style scoped>
.notification-management-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem;
}

.notification-management-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1rem;
}

.add-notification-btn {
  background-color: #4CAF50; /* Green */
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 1rem;
  font-size: 1rem;
}

.add-notification-btn:hover {
  background-color: #45a049;
}

.notification-list-card {
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 0.5rem;
  padding: 1.5rem;
}

.notification-list-title {
  font-size: 1.25rem;
  font-weight: 600;
  margin-bottom: 1rem;
}

.table-responsive {
  overflow-x: auto;
}

.data-table {
  min-width: 100%;
  background-color: white;
  border-collapse: collapse;
}

.data-table thead tr {
  border-bottom: 1px solid #e2e8f0;
}

.table-th {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
  background-color: #f7fafc;
  text-align: left;
  font-size: 0.75rem;
  font-weight: 600;
  color: #4a5568;
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.table-td {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #e2e8f0;
}

.btn-edit {
  background-color: #ecc94b;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
  margin-right: 0.5rem;
}

.btn-edit:hover {
  background-color: #d69e2e;
}

.btn-delete {
  background-color: #ef4444;
  color: white;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.75rem;
}

.btn-delete:hover {
  background-color: #dc2626;
}
</style>