<template>
  <div class="chat-container">
    <div class="chat-section">
      <div class="messages-display">
        <div v-for="message in messages" :key="message.id" class="message-item"
          :class="{ 'my-message': message.sender === 'You', 'other-message': message.sender !== 'You' }">
          <div class="message-header">
            <span class="sender-name">{{ message.sender }}</span>
            <span class="message-time">{{ message.time }}</span>
          </div>
          <div class="message-content">{{ message.text }}</div>
          <div v-if="message.file" class="message-file">
            <a :href="message.file.url" target="_blank">{{ message.file.name }}</a>
          </div>
        </div>
      </div>
      <div class="message-input-area">
        <input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="Nhập tin nhắn..." />
        <input type="file" @change="handleFileUpload" />
        <button @click="sendMessage">Gửi</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

interface Message {
  id: number;
  sender: string;
  text: string;
  time: string;
  file?: { name: string; url: string };
}

const messages = ref<Message[]>([
  { id: 1, sender: 'Alice', text: 'Chào quản trị viên!', time: '10:00 AM' },
  { id: 2, sender: 'Bob', text: 'Chào Alice!', time: '10:01 AM' },
  { id: 3, sender: 'Alice', text: 'Đây là một file đính kèm.', time: '10:05 AM', file: { name: 'document.pdf', url: '/path/to/document.pdf' } },
]);

const newMessage = ref('');

const sendMessage = () => {
  if (newMessage.value.trim() !== '') {
    messages.value.push({
      id: messages.value.length + 1,
      sender: 'You',
      text: newMessage.value,
      time: new Date().toLocaleTimeString(),
    });
    newMessage.value = '';
  }
};

const handleFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    const file = target.files[0];
    const fileUrl = URL.createObjectURL(file);
    messages.value.push({
      id: messages.value.length + 1,
      sender: 'You',
      text: `Đã gửi file: ${file.name}`,
      time: new Date().toLocaleTimeString(),
      file: { name: file.name, url: fileUrl },
    });
  }
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100%; /* Adjust as needed */
  border-radius: 8px;
  overflow: hidden;
  font-family: Arial, sans-serif;
}

.chat-section {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

.chat-section h3 {
  text-align: center;
  margin: 10px 0;
  color: #333;
}

.messages-display {
  flex-grow: 1;
  padding: 10px;
  overflow-y: auto;
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
}

.message-item {
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 5px;
  width: fit-content;
  max-width: 80%; /* Giới hạn độ rộng tối đa của tin nhắn */
}

.my-message {
  background-color: #e1ffc7; /* Màu xanh nhạt cho tin nhắn của bạn */
  align-self: flex-end;
  margin-left: auto;
}

.other-message {
  background-color: #f0f0f0; /* Màu xám nhạt cho tin nhắn của người khác */
  align-self: flex-start;
  margin-right: auto;
}

.message-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}

.sender-name {
  font-weight: bold;
  color: #075e54;
}

.message-time {
  font-size: 0.8em;
  color: #999;
}

.message-content {
  color: #333;
}

.message-file a {
  color: #007bff;
  text-decoration: none;
}

.message-file a:hover {
  text-decoration: underline;
}

.message-input-area {
  display: flex;
  padding: 10px;
  border-top: 1px solid #eee;
  background-color: #fff;
}

.message-input-area input[type="text"] {
  flex-grow: 1;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-right: 10px;
}

.message-input-area input[type="file"] {
  width: 100px; /* Adjust as needed */
  margin-right: 10px;
}

.message-input-area button {
  background-color: #28a745;
  color: white;
  border: none;
  padding: 8px 15px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.message-input-area button:hover {
  background-color: #218838;
}
</style>