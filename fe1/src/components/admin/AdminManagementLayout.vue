<template>
  <div class="admin-management-layout">
    <div class="main-content">
      <div class="content-header">
        <h1 class="page-title">
          <slot name="title"></slot>
        </h1>
        <div class="header-actions">
          <slot name="actions"></slot>
        </div>
      </div>
      
      <div class="content-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// Component layout chung cho các trang quản lý admin
</script>

<style scoped>
.admin-management-layout {
  min-height: 100vh;
  background-color: #f8f9fa;
}

.main-content {
  max-width: 100%;
  padding: 2rem;
}

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  padding: 1.5rem;
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.page-title {
  font-size: 1.875rem;
  font-weight: 700;
  color: #1f2937;
  margin: 0;
}

.header-actions {
  display: flex;
  gap: 1rem;
}

.content-body {
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}

/* Responsive design */
@media (max-width: 768px) {
  .main-content {
    padding: 1rem;
  }
  
  .content-header {
    flex-direction: column;
    gap: 1rem;
    align-items: stretch;
  }
  
  .page-title {
    font-size: 1.5rem;
    text-align: center;
  }
  
  .header-actions {
    justify-content: center;
  }
}
</style>