<template>
  <div class="notification-management-container">
    <h1 class="notification-management-title">Quản lý Thông báo</h1>

    <button @click="showAddNotificationModal" class="btn btn-primary add-notification-btn">
      Thêm Thông báo Mới
    </button>

    <NotificationForm :initialNotification="currentNotification" :isEditingProp="isEditing" :show="showNotificationFormModal"
      @save="handleSaveNotification" @cancel="handleCancelEdit" @close="showNotificationFormModal = false" />

    <BaseTable :headers="tableHeaders" :items="notifications" item-key="id" :has-actions="true" :clickable="true"
      @row-click="editNotification">
      <template #actions="{ item }">
        <button @click.stop="deleteNotification(item.id)"
          class="btn-delete">
          Xóa
        </button>
      </template>
    </BaseTable>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, reactive } from 'vue';
import NotificationForm from '../../components/admin/NotificationForm.vue';
import BaseTable from '../../components/common/BaseTable.vue';

interface Notification {
  id: number;
  title: string;
  content: string;
  targetRole: 'all' | 'student' | 'lecturer' | 'admin';
  createdAt: string;
}

interface TableHeader {
  text: string;
  value: string;
}

export default defineComponent({
  name: 'NotificationManagement',
  components: {
    NotificationForm,
    BaseTable,
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

    const tableHeaders = ref<TableHeader[]>([
      { text: 'Tiêu đề', value: 'title' },
      { text: 'Nội dung', value: 'content' },
      { text: 'Ngày tạo', value: 'createdAt' },
    ]);

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
      tableHeaders,
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