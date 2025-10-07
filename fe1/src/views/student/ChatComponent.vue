<template>
  <div class="chat-container">
    <div class="chat-mode-selector">
      <button :class="{ active: chatMode === 'direct' }" @click="chatMode = 'direct'">Chat Tổng</button>
      <button :class="{ active: chatMode === 'group' }" @click="chatMode = 'group'">Chat Nhóm</button>
      <button :class="{ active: chatMode === 'student-general' }" @click="chatMode = 'student-general'">Chat Chung Sinh Viên</button>
    </div>

    <div v-if="chatMode === 'direct'" class="chat-section">
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

    <div v-if="chatMode === 'group'" class="chat-section">
      <div class="messages-display">
        <div v-for="message in groupMessages" :key="message.id" class="message-item"
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
        <input type="text" v-model="newGroupMessage" @keyup.enter="sendGroupMessage" placeholder="Nhập tin nhắn nhóm..." />
        <input type="file" @change="handleGroupFileUpload" />
        <button @click="sendGroupMessage">Gửi</button>
      </div>
    </div>

    <div v-if="chatMode === 'student-general'" class="chat-section">
      <div class="messages-display">
        <div v-for="message in studentGeneralMessages" :key="message.id" class="message-item"
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
        <input type="text" v-model="newStudentGeneralMessage" @keyup.enter="sendStudentGeneralMessage" placeholder="Nhập tin nhắn chung..." />
        <input type="file" @change="handleStudentGeneralFileUpload" />
        <button @click="sendStudentGeneralMessage">Gửi</button>
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
  { id: 1, sender: 'Alice', text: 'Chào mọi người!', time: '10:00 AM' },
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

const chatMode = ref<'direct' | 'group' | 'student-general'>('direct'); // New state for chat mode

const groupMessages = ref<Message[]>([
  { id: 1, sender: 'GroupMember1', text: 'Chào nhóm!', time: '11:00 AM' },
  { id: 2, sender: 'GroupMember2', text: 'Chào bạn!', time: '11:01 AM' },
]);

const newGroupMessage = ref('');
const groupMembers = ['GroupMember1', 'GroupMember2', 'GroupMember3', 'You']; // Thêm các thành viên nhóm

const sendGroupMessage = () => {
  if (newGroupMessage.value.trim() !== '') {
    const randomSender = groupMembers[Math.floor(Math.random() * groupMembers.length)]; // Chọn ngẫu nhiên người gửi
    groupMessages.value.push({
      id: groupMessages.value.length + 1,
      sender: randomSender, // Sử dụng người gửi ngẫu nhiên
      text: newGroupMessage.value,
      time: new Date().toLocaleTimeString(),
    });
    newGroupMessage.value = '';
  }
};

const handleGroupFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    const file = target.files[0];
    const fileUrl = URL.createObjectURL(file);
    groupMessages.value.push({
      id: groupMessages.value.length + 1,
      sender: 'You',
      text: `Đã gửi file: ${file.name}`,
      time: new Date().toLocaleTimeString(),
      file: { name: file.name, url: fileUrl },
    });
  }
};

const studentGeneralMessages = ref<Message[]>([
  { id: 1, sender: 'StudentA', text: 'Chào các bạn sinh viên!', time: '09:00 AM' },
  { id: 2, sender: 'StudentB', text: 'Chào StudentA!', time: '09:02 AM' },
]);

const newStudentGeneralMessage = ref('');

const sendStudentGeneralMessage = () => {
  if (newStudentGeneralMessage.value.trim() !== '') {
    studentGeneralMessages.value.push({
      id: studentGeneralMessages.value.length + 1,
      sender: 'You',
      text: newStudentGeneralMessage.value,
      time: new Date().toLocaleTimeString(),
    });
    newStudentGeneralMessage.value = '';
  }
};

const handleStudentGeneralFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement;
  if (target.files && target.files.length > 0) {
    const file = target.files[0];
    const fileUrl = URL.createObjectURL(file);
    studentGeneralMessages.value.push({
      id: studentGeneralMessages.value.length + 1,
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

.chat-mode-selector {
  display: flex;
  justify-content: center;
  padding: 10px;
  background-color: #f0f2f5;
  border-bottom: 1px solid #eee;
}

.chat-mode-selector button {
  background-color: #e0e0e0;
  border: none;
  padding: 8px 15px;
  margin: 0 5px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.chat-mode-selector button:hover {
  background-color: #d0d0d0;
}

.chat-mode-selector button.active {
  background-color: #28a745;
  color: white;
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