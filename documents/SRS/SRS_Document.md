# Tài liệu Đặc tả Yêu cầu Phần mềm (SRS) - Ứng dụng Quản lý Đồ án Công nghệ Phần mềm

## 1. Giới thiệu

### 1.1. Mục đích

- Tài liệu này mô tả chi tiết các yêu cầu chức năng và phi chức năng của ứng dụng Quản lý Đồ án Công nghệ Phần mềm, nhằm cung cấp một cái nhìn toàn diện về hệ thống cho các bên liên quan.
- Môn đồ án Công nghệ Phần mềm này, mỗi năm có nhiều lớp, mỗi lớp có nhiều nhóm (tối đa 6 sinh viên/nhóm).

### 1.2. Phạm vi
- Ứng dụng này được phát triển để hỗ trợ sinh viên và giảng viên trong việc quản lý các đồ án công nghệ phần mềm.
- Phạm vi của hệ thống bao gồm các chức năng chính sau:
  - Quản lý thông tin sinh viên và nhóm.
  - Quản lý đề tài đồ án.
  - Quản lý tiến độ và báo cáo.
  - Quản lý thi cử.
  - Thông báo và nhắc nhở.
  - Phân quyền người dùng (Sinh viên, Giảng viên, Quản trị viên).

### 1.3. Định nghĩa, Viết tắt và Thuật ngữ
- **HUSC:** Đại học Khoa học Huế.
- **SRS:** Software Requirements Specification (Đặc tả Yêu cầu Phần mềm).
- **DB:** Database (Cơ sở dữ liệu).
- **API:** Application Programming Interface.
- **Người dùng:** Bao gồm Sinh viên, Giảng viên, Quản trị viên.
- **Đồ án:** Đồ án Công nghệ Phần mềm.

### 1.4. Tổng quan hệ thống
- Ứng dụng "Quản lý Đồ án Công nghệ Phần mềm" là một hệ thống web được xây dựng với mục tiêu số hóa và tối ưu hóa quy trình quản lý đồ án.
- Hệ thống sẽ có giao diện người dùng thân thiện, dễ sử dụng, cho phép sinh viên và giảng viên thực hiện các tác vụ liên quan đến đồ án một cách hiệu quả.
- Backend sẽ được phát triển bằng Spring Boot, cung cấp các API để xử lý logic nghiệp vụ và tương tác với cơ sở dữ liệu.
- Frontend sẽ sử dụng HTML, CSS và JavaScript để xây dựng giao diện người dùng.

## 2. Yêu cầu chức năng

### 2.1. Quản lý thông tin sinh viên và nhóm

#### 2.1.1. Đăng ký thông tin cá nhân của sinh viên
- Hệ thống phải cho phép sinh viên đăng ký thông tin cá nhân (mã sinh viên, họ tên, số điện thoại, email, khóa, ngành học).
- Hệ thống phải kiểm tra tính hợp lệ của dữ liệu đầu vào (định dạng email, độ dài số điện thoại).
- Hệ thống phải đảm bảo mã sinh viên là duy nhất.
- Hệ thống phải lưu trữ thông tin sinh viên vào cơ sở dữ liệu.
- Hệ thống phải cho phép sinh viên chỉnh sửa thông tin cá nhân của mình.

#### 2.1.2. Đăng nhập bằng tài khoản HUSC
- Hệ thống phải tích hợp với hệ thống xác thực của HUSC để cho phép sinh viên đăng nhập bằng tài khoản HUSC.
- Sau khi đăng nhập thành công qua HUSC, hệ thống phải tự động lấy thông tin cá nhân của sinh viên (mã sinh viên, họ tên, email) từ tài khoản HUSC.
- Hệ thống phải tự động điền hoặc cập nhật thông tin cá nhân của sinh viên trong hồ sơ của ứng dụng.

#### 2.1.3. Đăng ký nhóm
- Hệ thống phải cho phép sinh viên (chưa có nhóm) tạo nhóm mới hoặc tham gia nhóm đã có.
- Khi tạo nhóm mới, hệ thống phải cho phép sinh viên nhập tên nhóm và mời các thành viên khác (bằng mã sinh viên hoặc email).
- Hệ thống phải kiểm tra điều kiện tạo nhóm (ví dụ: số lượng thành viên, ưu tiên sinh viên cùng nhóm học phần).
- Hệ thống phải cập nhật trạng thái thành viên sau khi nhóm được tạo.
- Khi tham gia nhóm, hệ thống phải cho phép sinh viên tìm kiếm nhóm và gửi yêu cầu tham gia.
- Hệ thống phải cho phép nhóm trưởng duyệt hoặc từ chối yêu cầu tham gia nhóm.
- Hệ thống phải đảm bảo mỗi sinh viên chỉ thuộc một nhóm.
- Mỗi nhóm có thể có tối đa 6 thành viên (không phải nhóm lớp học phần)

#### 2.1.4. Hiển thị thông tin nhóm
- Hệ thống phải hiển thị danh sách các nhóm cho tất cả người dùng (sinh viên, giảng viên, quản trị viên).
- Hệ thống phải hiển thị chi tiết thông tin của một nhóm (tên nhóm, danh sách thành viên) khi người dùng chọn.
- Hệ thống phải đảm bảo dữ liệu thông tin nhóm được cập nhật theo thời gian thực.

#### 2.1.5. Hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm
- Hệ thống phải hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm.
- Hệ thống phải tự động lập nhóm cho các sinh viên chưa có nhóm khi đến hạn đăng ký, ưu tiên sinh viên cùng nhóm lớp học phần. Nếu lớp hết người nhưng nhóm vẫn chưa đủ, gom từ các nhóm khác.
- Thông tin hiển thị phải bao gồm mã sinh viên, họ tên, và tên nhóm (nếu đã có nhóm).
- Hệ thống phải tự động cập nhật danh sách này khi có sự thay đổi về trạng thái nhóm của sinh viên.

#### 2.1.6. Cho phép sinh viên gửi lời mời tham gia nhóm
- Hệ thống phải cho phép sinh viên (nhóm trưởng) gửi lời mời tham gia nhóm đến các sinh viên khác.
- Hệ thống phải thông báo cho sinh viên được mời và cho phép họ chấp nhận hoặc từ chối lời mời.
- Hệ thống phải cập nhật trạng thái nhóm sau khi lời mời được chấp nhận.

#### 2.1.7. Quản lý thông tin giảng viên hướng dẫn
- Hệ thống phải cho phép quản trị viên thêm mới, sửa, xóa thông tin giảng viên hướng dẫn.
- Thông tin giảng viên bao gồm: tên, email, số lượng nhóm có thể hướng dẫn.
- Hệ thống phải kiểm tra tính hợp lệ của dữ liệu (ví dụ: email duy nhất).

### 2.2. Quản lý đề tài đồ án

#### 2.2.1. Danh sách các đề tài tham khảo
- Hệ thống phải hiển thị danh sách các đề tài tham khảo cho sinh viên và giảng viên.
- Danh sách phải bao gồm tên đề tài, mô tả ngắn gọn và các thông tin liên quan.
- Hệ thống phải cung cấp chức năng tìm kiếm và lọc đề tài.

#### 2.2.2. Sinh viên/nhóm đăng ký đề tài
- Hệ thống phải cho phép sinh viên/nhóm trưởng đăng ký đề tài từ danh sách tham khảo hoặc đề xuất đề tài mới.
- Khi chọn đề tài từ danh sách, hệ thống phải kiểm tra tính khả dụng của đề tài.
- Khi đề xuất đề tài mới, hệ thống phải cho phép nhập tên, mô tả, mục tiêu, công nghệ dự kiến và gửi đến giảng viên/quản trị viên để duyệt.
- Hệ thống phải đảm bảo mỗi nhóm chỉ được đăng ký một đề tài.

#### 2.2.3. Kiểm tra đề tài trùng lặp
- Hệ thống phải tự động kiểm tra trùng lặp đề tài khi sinh viên/nhóm đăng ký hoặc đề xuất.
- Hệ thống phải thông báo cho người dùng nếu phát hiện trùng lặp.

### 2.3. Quản lý tiến độ và báo cáo

#### 2.3.1. Nộp báo cáo
- Hệ thống sẽ tự động lấy nội dung báo cáo từ URL được cung cấp tại thời điểm deadline.
- Hệ thống sẽ không cho phép nộp lại báo cáo sau khi đã nộp.
- Khi deadline được gia hạn, hệ thống sẽ không gia hạn báo cáo mà thay vào đó sẽ tạo ra các phiên bản báo cáo mới (v2, v3,...).

#### 2.3.2. Xác nhận nộp báo cáo
- Sau khi báo cáo được nộp (tự động), người dùng sẽ nhận được xác nhận ngay lập tức.
- Xác nhận này bao gồm thời gian nộp, phiên bản báo cáo và liên kết đến báo cáo.

#### 2.3.3. Quyền truy cập báo cáo
- Tất cả thành viên trong nhóm, giảng viên và quản trị viên đều có thể xem báo cáo đã nộp.

#### 2.3.4. Trạng thái báo cáo
- Báo cáo có các trạng thái sau: "Đã nộp", "Đang chờ duyệt", "Đã duyệt", "Cần chỉnh sửa", "Bị từ chối".
- Quản trị viên và giảng viên có quyền thay đổi trạng thái của báo cáo.

#### 2.3.5. Thông báo về báo cáo
- Hệ thống sẽ gửi các thông báo cần thiết cho người dùng về việc nộp báo cáo (ví dụ: nhắc nhở sắp đến deadline, thông báo đã nộp thành công, thông báo báo cáo bị từ chối).

#### 2.3.6. Kế hoạch làm việc hàng tuần
- Hệ thống phải cho phép nhóm lập kế hoạch làm việc hàng tuần trực tiếp trong ứng dụng hoặc liên kết đến Google Sheet.
- Nếu lập kế hoạch trong ứng dụng, hệ thống phải cho phép nhập công việc, người phụ trách, thời hạn, trạng thái và hiển thị kế hoạch.

### 2.4. Quản lý thi cử

#### 2.4.1. Thông tin về hình thức thi kết thúc học phần
- Hệ thống phải cho phép giảng viên/quản trị viên nhập và hiển thị thông tin về hình thức thi kết thúc học phần cho sinh viên.

#### 2.4.2. Quản lý điểm quá trình học tập
- Hệ thống phải cho phép giảng viên nhập điểm quá trình cho từng nhóm/sinh viên.
- Hệ thống phải tính toán và hiển thị điểm quá trình tổng kết.
- Giảng viên có thể tạo thông báo chung cho tất cả sinh viên hoặc nhóm cụ thể.
- Giảng viên có thể tạo ngày deadline cho các hoạt động như nộp báo cáo, đăng ký nhóm, bảo vệ đồ án.
- Giảng viên có thể yêu cầu sinh viên đổi đề tài nếu thấy không phù hợp.

### 2.5. Thông báo và nhắc nhở

#### 2.5.1. Nhắc nhở hạn đăng ký
- Hệ thống phải gửi nhắc nhở về các hạn đăng ký (ví dụ: hạn đăng ký nhóm, hạn đăng ký đề tài).

#### 2.5.2. Thông báo chung
- Hệ thống phải cho phép quản trị viên gửi thông báo chung đến tất cả người dùng.

### 2.6. Phân quyền
- Hệ thống phải hỗ trợ các vai trò người dùng: Sinh viên, Giảng viên, Quản trị viên.
- Mỗi vai trò phải có các quyền truy cập và chức năng riêng biệt.

## 3. Yêu cầu phi chức năng

### 3.1. Hiệu năng
- **Thời gian phản hồi:** Hệ thống phải phản hồi các yêu cầu của người dùng trong vòng 2 giây đối với 90% các tác vụ thông thường (ví dụ: tải trang, tìm kiếm, đăng nhập).
- **Khả năng chịu tải:** Hệ thống phải hỗ trợ ít nhất 100 người dùng đồng thời mà không ảnh hưởng đáng kể đến hiệu năng.

### 3.2. Bảo mật
- **Xác thực:** Hệ thống phải sử dụng cơ chế xác thực an toàn (tích hợp HUSC SSO) để đảm bảo chỉ người dùng hợp lệ mới có thể truy cập.
- **Phân quyền:** Hệ thống phải thực thi cơ chế phân quyền dựa trên vai trò để đảm bảo người dùng chỉ có thể truy cập các chức năng và dữ liệu mà họ được phép.
- **Bảo vệ dữ liệu:** Dữ liệu nhạy cảm (ví dụ: mật khẩu, thông tin cá nhân) phải được mã hóa khi lưu trữ và truyền tải.
- **Chống tấn công:** Hệ thống phải có khả năng chống lại các cuộc tấn công phổ biến như SQL Injection, XSS, CSRF.

### 3.3. Khả năng sử dụng
- **Giao diện người dùng:** Giao diện phải trực quan, dễ hiểu và dễ sử dụng cho tất cả các đối tượng người dùng (sinh viên, giảng viên, quản trị viên).
- **Tính nhất quán:** Giao diện và luồng công việc phải nhất quán trên toàn hệ thống.
- **Thông báo lỗi:** Hệ thống phải cung cấp các thông báo lỗi rõ ràng, dễ hiểu và hướng dẫn người dùng cách khắc phục.

### 3.4. Độ tin cậy
- **Khả năng phục hồi:** Hệ thống phải có khả năng phục hồi sau lỗi một cách nhanh chóng và tự động, đảm bảo mất mát dữ liệu tối thiểu.
- **Tính sẵn sàng:** Hệ thống phải sẵn sàng hoạt động 99.5% thời gian.

### 3.5. Khả năng bảo trì
- **Cấu trúc mã:** Mã nguồn phải được tổ chức rõ ràng, dễ đọc và tuân thủ các nguyên tắc lập trình tốt.
- **Tài liệu:** Mã nguồn phải được tài liệu hóa đầy đủ (comment, README, v.v.).
- Khả năng mở rộng: Hệ thống phải được thiết kế để dễ dàng mở rộng và thêm các tính năng mới trong tương lai.

### 3.6. Công nghệ sử dụng
- **Backend**: Spring Boot
- **Frontend**: Tailwind CSS

### 3.6. Khả năng tương thích
- **Trình duyệt:** Hệ thống phải tương thích với các trình duyệt web phổ biến hiện nay (Chrome, Firefox, Edge, Safari).
- **Hệ điều hành:** Hệ thống phải hoạt động tốt trên các hệ điều hành phổ biến (Windows, macOS, Linux).
- **Tích hợp:** Hệ thống phải có khả năng tích hợp với hệ thống xác thực của HUSC và có thể tham chiếu đến Google Sheet.

### 3.7. Yêu cầu về môi trường hoạt động
- **Hệ điều hành máy chủ:** Linux (Ubuntu/CentOS) hoặc Windows Server.
- **Cơ sở dữ liệu:** MySQL hoặc PostgreSQL.
- **Môi trường chạy Backend:** Java Development Kit (JDK) 11 trở lên.
- **Môi trường chạy Frontend:** Trình duyệt web hiện đại.

## 4. Các yêu cầu khác

### 4.1. Yêu cầu về dữ liệu
- **Tính toàn vẹn dữ liệu:** Hệ thống phải đảm bảo tính toàn vẹn và nhất quán của dữ liệu.
- **Sao lưu và phục hồi:** Dữ liệu phải được sao lưu định kỳ và có cơ chế phục hồi khi cần thiết.

### 4.2. Yêu cầu về triển khai
- Hệ thống phải có thể triển khai trên môi trường máy chủ vật lý hoặc ảo hóa.
- Cung cấp tài liệu hướng dẫn triển khai chi tiết.