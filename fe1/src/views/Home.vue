<template>
  <div class="requirement-outline">
    <h1 class="outline-title">Requirement Outline</h1>

    <div class="section">
      <h2 class="section-title">Nhóm KH-40</h2>
      <p><strong>Đề tài:</strong> Xây dựng ứng dụng quản lý đồ án công nghệ phần mềm</p>
      <StudentChatComponent v-if="userRole === 'student'" />
  </div>

    <div class="section">
      <h2 class="section-title">Ý tưởng của nhóm</h2>
      <p>Xây dựng ứng dụng hỗ trợ sinh viên trong việc tự động hóa điền thông tin cá nhân, tìm và tham gia nhóm thực hiện đồ án, đồng thời giúp giảng viên dễ dàng quản lý, theo dõi các nhóm đồ án môn Công nghệ Phần mềm.</p>
    </div>

    <div class="section">
      <h2 class="section-title">Đối tượng sử dụng hướng đến</h2>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Đối tượng</th>
            <th>Mô tả</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Sinh viên</td>
            <td>Tìm kiếm và tham gia/thoát nhóm, đăng ký đề tài, nhận/gửi báo cáo nhóm lên classroom, xem tiến độ và điểm số, quên mật khẩu, nhắc lịch/nhiệm vụ, thông báo tự động, chat nhóm, gửi tài liệu, theo dõi lịch sử hoạt động, báo cáo tiến độ cá nhân.</td>
            <td>✅</td>
          </tr>
          <tr>
            <td>Nhóm trưởng</td>
            <td>Thực hiện quản trị nhóm (thêm/xóa thành viên, gửi báo cáo nhóm, cập nhật kế hoạch), quản lý phân công công việc, thống kê tiến độ thành viên, gửi thông báo nội bộ nhóm.</td>
            <td>❌</td>
          </tr>
          <tr>
            <td>Giảng viên</td>
            <td>Duyệt nhóm/đề tài, phản hồi báo cáo, chấm điểm, gửi thông báo, theo dõi tiến độ, quản lý tài liệu tham khảo, chat/trao đổi trực tuyến, thống kê/xuất dữ liệu điểm số & tiến độ, đánh giá tiến độ, gửi nhận xét tự động.</td>
            <td>✅</td>
          </tr>
          <tr>
            <td>Quản trị viên</td>
            <td>Quản lý toàn bộ hệ thống, phân quyền, quản lý tài khoản sinh viên/giảng viên, cấu hình các quy định chung, Thống kê tất cả nhóm/đề tài, Xuất/nhập dữ liệu tổng hợp, Quản lý phản hồi/lỗi hệ thống.</td>
            <td>✅</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="section">
      <h2 class="section-title">Chức năng của User</h2>
      <h3 class="sub-section-title">1. Quản lý tài khoản</h3>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Chức năng</th>
            <th>Mô tả</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Đăng nhập bằng tài khoản HUSC</td>
            <td>Mỗi đối tượng người dùng có các chức năng khác nhau. Đăng nhập SINGLE SIGN-ON qua <a href="https://student.husc.edu.vn/" target="_blank">https://student.husc.edu.vn/</a>. Nếu xác thực thất bại, người dùng không được truy cập và sử dụng hệ thống.</td>
            <td>✅</td>
          </tr>
          <tr>
            <td>Cập nhật trang cá nhân</td>
            <td></td>
            <td>✅</td>
          </tr>
        </tbody>
      </table>

      <h3 class="sub-section-title">2. Sử dụng tài khoản</h3>
      <h4 class="role-title">a. Đối tượng Sinh viên:</h4>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Chức năng</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr><td>Tạo/Tham gia nhóm, mời hoặc nhận lời mời vào nhóm, rời nhóm, chat/gửi tài liệu</td><td>✅</td></tr>
          <tr><td>Xem thông tin nhóm, thành viên nhóm, đề tài nhóm.</td><td>✅</td></tr>
          <tr><td>Đăng ký hoặc đề xuất đề tài đồ án (cá nhân/nhóm).</td><td>✅</td></tr>
          <tr><td>Xem danh sách đề tài tham khảo.</td><td>✅</td></tr>
          <tr><td>Nộp báo cáo kế hoạch, báo cáo tiến độ hoặc kết quả (cùng nhóm trưởng).</td><td>✅</td></tr>
          <tr><td>Nhận, xem điểm các kỳ, điểm tổng kết.</td><td>❌</td></tr>
          <tr><td>Chỉ xem/nộp/xem báo cáo của bản thân và nhóm mình.(mỗi sinh viên chỉ được tham gia một nhóm)</td><td>❌</td></tr>
          <tr><td>Theo dõi lịch sử hoạt động</td><td>❌</td></tr>
          <tr><td>Quên mật khẩu</td><td>❌</td></tr>
        </tbody>
      </table>

      <h4 class="role-title">b. Đối tượng Nhóm trưởng(quyền riêng biệt trong nhóm)</h4>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Chức năng</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr><td>Gửi báo cáo tiến độ, kết quả công việc định kỳ cho giảng viên.</td><td>✅</td></tr>
          <tr><td>Quản lý thành viên nhóm, duyệt lời mời, gửi lời mời thành viên.</td><td>✅</td></tr>
          <tr><td>Cập nhật kế hoạch làm việc của nhóm.</td><td>✅</td></tr>
          <tr><td>Quản lý việc nộp báo cáo nhóm.</td><td>✅</td></tr>
          <tr><td>(nhóm trưởng khi rời nhóm phải chuyển quyền trưởng nhóm mới)</td><td>❌</td></tr>
          <tr><td>Quản lý phân công công việc</td><td>❌</td></tr>
          <tr><td>Thống kê tiến độ thành viên</td><td>❌</td></tr>
          <tr><td>Gửi thông báo nội bộ nhóm</td><td>❌</td></tr>
        </tbody>
      </table>

      <h4 class="role-title">c. Đối tượng Giảng viên</h4>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Chức năng</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr><td>Quản lý, xem thông tin sinh viên và nhóm.</td><td>✅</td></tr>
          <tr><td>Duyệt/Quản lý nhóm, duyệt/thêm đề tài, duyệt đề xuất đề tài mới.</td><td>✅</td></tr>
          <tr><td>Hướng dẫn, nhận xét/phản hồi báo cáo nhóm và tiến độ.</td><td>✅</td></tr>
          <tr><td>Nhập điểm thành phần, điểm tổng kết.</td><td>❌</td></tr>
          <tr><td>Tạo/thêm thông báo, tạo deadline/quản lý lịch bảo vệ.</td><td>✅</td></tr>
          <tr><td>Xem thông tin và điểm của tất cả các nhóm, sinh viên thuộc phạm vi quản lý.</td><td>✅</td></tr>
          <tr><td>Hướng dẫn, nhận xét/phản hồi báo cáo nhóm và tiến độ.</td><td>✅</td></tr>
          <tr><td>Theo dõi tiến độ</td><td>✅</td></tr>
          <tr><td>Quản lý tài liệu tham khảo</td><td>❌</td></tr>
          <tr><td>Chat trao đổi trực tiếp</td><td>❌</td></tr>
          <tr><td>Thống kê/xuất dữ liệu điểm số & tiến độ, đánh giá tiến độ, gửi nhận xét tự động</td><td>❌</td></tr>
        </tbody>
      </table>
    </div>

    <div class="section">
      <h2 class="section-title">Chức năng của Admin</h2>
      <table class="feature-table">
        <thead>
          <tr>
            <th>Chức năng</th>
            <th>Trạng thái</th>
          </tr>
        </thead>
        <tbody>
          <tr><td>Quản lý toàn bộ người dùng hệ thống: sinh viên, giảng viên (tạo, phân quyền, sửa, xóa).</td><td>✅</td></tr>
          <tr><td>Quản lý, duyệt đề tài, cấu hình các mốc thời gian/quy định chung.</td><td>✅</td></tr>
          <tr><td>Thống kê tất cả nhóm/đề tài</td><td>✅</td></tr>
          <tr><td>Xuất nhập dữ liệu tổng hợp</td><td>❌</td></tr>
          <tr><td>Quản lý phản hồi/lỗi hệ thống</td><td>❌</td></tr>
        </tbody>
      </table>
    </div>

    <div class="section">
      <h2 class="section-title">Kỹ thuật</h2>
      <p>Java (mô hình MVC framework Struts)</p>
      <p>Hệ quản trị cơ sở dữ liệu SQL Server</p>
    </div>

    <div class="section">
      <h2 class="section-title">Nhận xét về Sheet Plan</h2>
      <p>Sheet Plan: mô tả còn sơ sài, cần liệt kê chi tiết hơn các chức năng mô tả ở RO để phân công cho các thành viên làm</p>
    </div>
  </div>
</template>

<style scoped>
.requirement-outline {
  padding: 20px;
  font-family: Arial, sans-serif;
}

.outline-title {
  font-size: 2.5rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 30px;
  text-align: center;
}

.section {
  margin-bottom: 40px;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.section-title {
  font-size: 1.8rem;
  font-weight: bold;
  color: #0056b3;
  margin-bottom: 15px;
  border-bottom: 2px solid #0056b3;
  padding-bottom: 5px;
}

.sub-section-title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #007bff;
  margin-top: 25px;
  margin-bottom: 10px;
}

.role-title {
  font-size: 1.2rem;
  font-weight: bold;
  color: #28a745;
  margin-top: 20px;
  margin-bottom: 8px;
}

.feature-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

.feature-table th, .feature-table td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

.feature-table th {
  background-color: #e9ecef;
  font-weight: bold;
  color: #495057;
}

.feature-table tbody tr:nth-child(even) {
  background-color: #f2f2f2;
}

.feature-table tbody tr:hover {
  background-color: #e2e6ea;
}

.feature-table td:last-child {
  text-align: center;
  width: 80px; /* Adjust width for status column */
}

.feature-table td a {
  color: #007bff;
  text-decoration: none;
}

.feature-table td a:hover {
  text-decoration: underline;
}
</style>

<script setup lang="ts">
import { ref, computed } from 'vue';
import StudentChatComponent from '../views/student/ChatComponent.vue';

const userRole = ref(localStorage.getItem('userRole') || 'guest');

</script>