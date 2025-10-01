<template>
  <div id="app">
    <AdminSidebar />
    <div class="main-content">
      <router-view />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import AdminSidebar from './components/AdminSidebar.vue';

export default defineComponent({
  name: 'App',
  components: {
    AdminSidebar,
  },
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  min-width: 100%;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  display: flex;
  min-height: 100vh;
}

.main-content {
  flex-grow: 1;
  /* padding: 20px; */
}
</style>
