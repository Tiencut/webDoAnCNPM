# Test Cases for Project Management System

This document outlines the test cases for the "Project Management System" application, based on the functional requirements defined in `Requirement_Outline.md` and `SRS_Document.md`.

## 1. Quản lý thông tin sinh viên và nhóm (Student and Group Management)

### 1.1. Đăng ký thông tin cá nhân của sinh viên (Student Personal Information Registration)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_SV_REG_001 | Đăng ký thành công với dữ liệu hợp lệ | Sinh viên chưa đăng ký thông tin cá nhân | 1. Truy cập trang đăng ký. <br> 2. Nhập đầy đủ và hợp lệ các thông tin. <br> 3. Nhấn nút "Đăng ký". | Mã SV: SV001, Họ tên: Nguyễn Văn A, SĐT: 0912345678, Email: nva@husc.edu.vn, Khóa: K45, Ngành: CNTT | Hệ thống hiển thị thông báo "Đăng ký thành công". Thông tin SV được lưu vào DB. | Pending |
| TC_SV_REG_002 | Đăng ký thất bại do thiếu thông tin bắt buộc | Sinh viên chưa đăng ký thông tin cá nhân | 1. Truy cập trang đăng ký. <br> 2. Bỏ trống trường "Họ tên". <br> 3. Nhấn nút "Đăng ký". | Mã SV: SV002, SĐT: 0912345679, Email: nvb@husc.edu.vn, Khóa: K45, Ngành: CNTT | Hệ thống hiển thị thông báo lỗi "Vui lòng nhập Họ tên". Thông tin SV không được lưu vào DB. | Pending |
| TC_SV_REG_003 | Đăng ký thất bại do định dạng email không hợp lệ | Sinh viên chưa đăng ký thông tin cá nhân | 1. Truy cập trang đăng ký. <br> 2. Nhập email sai định dạng. <br> 3. Nhấn nút "Đăng ký". | Mã SV: SV003, Họ tên: Nguyễn Văn C, SĐT: 0912345680, Email: nvc@husc, Khóa: K45, Ngành: CNTT | Hệ thống hiển thị thông báo lỗi "Định dạng email không hợp lệ". Thông tin SV không được lưu vào DB. | Pending |
| TC_SV_REG_004 | Đăng ký thất bại do mã sinh viên đã tồn tại | Sinh viên SV001 đã tồn tại trong DB | 1. Truy cập trang đăng ký. <br> 2. Nhập mã sinh viên đã tồn tại. <br> 3. Nhấn nút "Đăng ký". | Mã SV: SV001, Họ tên: Nguyễn Văn D, SĐT: 0912345681, Email: nvd@husc.edu.vn, Khóa: K45, Ngành: CNTT | Hệ thống hiển thị thông báo lỗi "Mã sinh viên đã tồn tại". Thông tin SV không được lưu vào DB. | Pending |

### 1.2. Đăng nhập bằng tài khoản HUSC (HUSC Account Login)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_HUSC_LOGIN_001 | Đăng nhập thành công với tài khoản HUSC hợp lệ | Tài khoản HUSC hợp lệ và có kết nối đến hệ thống HUSC | 1. Truy cập trang đăng nhập ứng dụng. <br> 2. Nhấn nút "Đăng nhập bằng HUSC". <br> 3. Nhập tài khoản và mật khẩu HUSC hợp lệ trên trang HUSC. | Tài khoản HUSC: user_husc, Mật khẩu HUSC: pass_husc | Hệ thống chuyển hướng về ứng dụng, tự động điền/cập nhật thông tin cá nhân SV và đăng nhập thành công. | Pending |
| TC_HUSC_LOGIN_002 | Đăng nhập thất bại do sai tài khoản/mật khẩu HUSC | Có kết nối đến hệ thống HUSC | 1. Truy cập trang đăng nhập ứng dụng. <br> 2. Nhấn nút "Đăng nhập bằng HUSC". <br> 3. Nhập tài khoản hoặc mật khẩu HUSC không hợp lệ trên trang HUSC. | Tài khoản HUSC: wrong_user, Mật khẩu HUSC: wrong_pass | Hệ thống HUSC hiển thị thông báo lỗi đăng nhập. Ứng dụng không đăng nhập được. | Pending |
| TC_HUSC_LOGIN_003 | Đăng nhập thất bại do lỗi kết nối HUSC | Không có kết nối đến hệ thống HUSC | 1. Truy cập trang đăng nhập ứng dụng. <br> 2. Nhấn nút "Đăng nhập bằng HUSC". | N/A | Hệ thống hiển thị thông báo lỗi "Không thể kết nối đến hệ thống HUSC" hoặc tương tự. | Pending |

### 1.3. Đăng ký nhóm (Group Registration)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_GROUP_REG_001 | Tạo nhóm mới thành công với thành viên hợp lệ | Sinh viên chưa có nhóm | 1. Đăng nhập với vai trò SV. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Chọn "Tạo nhóm mới". <br> 4. Nhập tên nhóm và mời các thành viên hợp lệ. <br> 5. Nhấn nút "Tạo nhóm". | Tên nhóm: Nhóm Đồ án 1, Thành viên: SV002, SV003 | Hệ thống hiển thị thông báo "Tạo nhóm thành công". Nhóm được tạo, SV001 là nhóm trưởng, SV002, SV003 là thành viên. | Pending |
| TC_GROUP_REG_002 | Tạo nhóm mới thất bại do tên nhóm trùng lặp | Nhóm "Nhóm Đồ án 1" đã tồn tại | 1. Đăng nhập với vai trò SV. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Chọn "Tạo nhóm mới". <br> 4. Nhập tên nhóm đã tồn tại. <br> 5. Nhấn nút "Tạo nhóm". | Tên nhóm: Nhóm Đồ án 1, Thành viên: SV004, SV005 | Hệ thống hiển thị thông báo lỗi "Tên nhóm đã tồn tại". Nhóm không được tạo. | Pending |
| TC_GROUP_REG_003 | Tham gia nhóm thành công (nhóm trưởng duyệt) | Nhóm "Nhóm Đồ án 2" tồn tại, SV006 chưa có nhóm | 1. Đăng nhập với vai trò SV006. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Chọn "Tham gia nhóm". <br> 4. Tìm kiếm và gửi yêu cầu tham gia "Nhóm Đồ án 2". <br> 5. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 2". <br> 6. Duyệt yêu cầu của SV006. | Mã nhóm: Nhóm Đồ án 2, Yêu cầu tham gia từ SV006 | Hệ thống hiển thị thông báo "Tham gia nhóm thành công" cho SV006. SV006 trở thành thành viên của "Nhóm Đồ án 2". | Pending |
| TC_GROUP_REG_004 | Tham gia nhóm thất bại (nhóm trưởng từ chối) | Nhóm "Nhóm Đồ án 3" tồn tại, SV007 chưa có nhóm | 1. Đăng nhập với vai trò SV007. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Chọn "Tham gia nhóm". <br> 4. Tìm kiếm và gửi yêu cầu tham gia "Nhóm Đồ án 3". <br> 5. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 3". <br> 6. Từ chối yêu cầu của SV007. | Mã nhóm: Nhóm Đồ án 3, Yêu cầu tham gia từ SV007 | Hệ thống hiển thị thông báo "Yêu cầu tham gia nhóm bị từ chối" cho SV007. SV007 vẫn chưa có nhóm. | Pending |
| TC_GROUP_REG_005 | Tạo nhóm thất bại do số lượng thành viên vượt quá giới hạn | Sinh viên chưa có nhóm, giới hạn thành viên là 3 | 1. Đăng nhập với vai trò SV. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Chọn "Tạo nhóm mới". <br> 4. Nhập tên nhóm và mời 4 thành viên. <br> 5. Nhấn nút "Tạo nhóm". | Tên nhóm: Nhóm Đồ án 4, Thành viên: SV008, SV009, SV010, SV011 | Hệ thống hiển thị thông báo lỗi "Số lượng thành viên vượt quá giới hạn cho phép". Nhóm không được tạo. | Pending |

### 1.4. Hiển thị thông tin nhóm (Display Group Information)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_GROUP_INFO_001 | Hiển thị danh sách nhóm thành công | Có ít nhất 1 nhóm tồn tại trong DB | 1. Đăng nhập với bất kỳ vai trò nào. <br> 2. Truy cập trang danh sách nhóm. | N/A | Hệ thống hiển thị danh sách tất cả các nhóm hiện có, bao gồm tên nhóm. | Pending |
| TC_GROUP_INFO_002 | Hiển thị chi tiết thông tin nhóm thành công | Nhóm "Nhóm Đồ án 1" tồn tại với các thành viên | 1. Đăng nhập với bất kỳ vai trò nào. <br> 2. Truy cập trang danh sách nhóm. <br> 3. Chọn "Nhóm Đồ án 1". | Tên nhóm: Nhóm Đồ án 1 | Hệ thống hiển thị trang chi tiết của "Nhóm Đồ án 1", bao gồm tên nhóm và danh sách các thành viên (mã SV, họ tên). | Pending |
| TC_GROUP_INFO_003 | Không tìm thấy nhóm khi xem chi tiết | Nhóm không tồn tại | 1. Đăng nhập với bất kỳ vai trò nào. <br> 2. Truy cập trang danh sách nhóm. <br> 3. Cố gắng xem chi tiết một nhóm không tồn tại (ví dụ: qua URL trực tiếp). | Tên nhóm: Nhóm Không Tồn Tại | Hệ thống hiển thị thông báo lỗi "Không tìm thấy nhóm" hoặc trang 404. | Pending |

### 1.5. Hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm (Display List of Grouped and Ungrouped Students)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_SV_LIST_001 | Hiển thị danh sách sinh viên đã có nhóm và chưa có nhóm thành công | Có sinh viên đã có nhóm và chưa có nhóm trong DB | 1. Đăng nhập với vai trò Giảng viên/Quản trị viên. <br> 2. Truy cập trang quản lý sinh viên/nhóm. | N/A | Hệ thống hiển thị hai danh sách riêng biệt: <br> - Danh sách sinh viên đã có nhóm (Mã SV, Họ tên, Tên nhóm). <br> - Danh sách sinh viên chưa có nhóm (Mã SV, Họ tên). | Pending |
| TC_SV_LIST_002 | Danh sách sinh viên chưa có nhóm trống | Tất cả sinh viên đều đã có nhóm | 1. Đăng nhập với vai trò Giảng viên/Quản trị viên. <br> 2. Truy cập trang quản lý sinh viên/nhóm. | N/A | Hệ thống hiển thị danh sách sinh viên đã có nhóm và thông báo "Không có sinh viên nào chưa có nhóm" hoặc danh sách trống cho phần chưa có nhóm. | Pending |
| TC_SV_LIST_003 | Danh sách sinh viên đã có nhóm trống | Tất cả sinh viên đều chưa có nhóm | 1. Đăng nhập với vai trò Giảng viên/Quản trị viên. <br> 2. Truy cập trang quản lý sinh viên/nhóm. | N/A | Hệ thống hiển thị danh sách sinh viên chưa có nhóm và thông báo "Không có sinh viên nào đã có nhóm" hoặc danh sách trống cho phần đã có nhóm. | Pending |

### 1.6. Cho phép sinh viên gửi lời mời tham gia nhóm (Allow Students to Send Group Invitations)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_INVITE_001 | Gửi lời mời thành công đến sinh viên hợp lệ | SV001 là nhóm trưởng, SV002 chưa có nhóm | 1. Đăng nhập với vai trò SV001 (nhóm trưởng). <br> 2. Truy cập trang quản lý nhóm. <br> 3. Chọn "Mời thành viên". <br> 4. Nhập mã SV002. <br> 5. Nhấn "Gửi lời mời". | Mã SV: SV002 | Hệ thống hiển thị thông báo "Gửi lời mời thành công". SV002 nhận được thông báo lời mời. | Pending |
| TC_INVITE_002 | Gửi lời mời thất bại do sinh viên đã có nhóm | SV001 là nhóm trưởng, SV003 đã có nhóm | 1. Đăng nhập với vai trò SV001 (nhóm trưởng). <br> 2. Truy cập trang quản lý nhóm. <br> 3. Chọn "Mời thành viên". <br> 4. Nhập mã SV003. <br> 5. Nhấn "Gửi lời mời". | Mã SV: SV003 | Hệ thống hiển thị thông báo lỗi "Sinh viên đã có nhóm". Lời mời không được gửi. | Pending |
| TC_INVITE_003 | Sinh viên chấp nhận lời mời thành công | SV001 đã gửi lời mời đến SV002, SV002 chưa có nhóm | 1. Đăng nhập với vai trò SV002. <br> 2. Truy cập thông báo/lời mời. <br> 3. Chấp nhận lời mời từ SV001. | N/A | Hệ thống hiển thị thông báo "Chấp nhận lời mời thành công". SV002 trở thành thành viên của nhóm SV001. | Pending |
| TC_INVITE_004 | Sinh viên từ chối lời mời thành công | SV001 đã gửi lời mời đến SV002, SV002 chưa có nhóm | 1. Đăng nhập với vai trò SV002. <br> 2. Truy cập thông báo/lời mời. <br> 3. Từ chối lời mời từ SV001. | N/A | Hệ thống hiển thị thông báo "Từ chối lời mời thành công". SV002 vẫn chưa có nhóm. | Pending |

### 1.7. Quản lý thông tin giảng viên hướng dẫn (Instructor Information Management)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_GV_MANAGE_001 | Thêm mới giảng viên thành công | Đăng nhập với vai trò Quản trị viên | 1. Truy cập trang quản lý giảng viên. <br> 2. Nhấn nút "Thêm mới". <br> 3. Nhập thông tin giảng viên hợp lệ. <br> 4. Nhấn "Lưu". | Tên GV: Trần Thị B, Email: ttb@husc.edu.vn, Số lượng nhóm: 5 | Hệ thống hiển thị thông báo "Thêm mới thành công". GV được thêm vào DB và hiển thị trong danh sách. | Pending |
| TC_GV_MANAGE_002 | Sửa thông tin giảng viên thành công | Giảng viên "Nguyễn Văn A" tồn tại trong DB | 1. Truy cập trang quản lý giảng viên. <br> 2. Chọn GV "Nguyễn Văn A". <br> 3. Sửa thông tin (ví dụ: số lượng nhóm). <br> 4. Nhấn "Lưu". | Tên GV: Nguyễn Văn A, Email: nva@husc.edu.vn, Số lượng nhóm: 7 (thay đổi từ 5) | Hệ thống hiển thị thông báo "Cập nhật thành công". Thông tin GV được cập nhật trong DB và hiển thị đúng. | Pending |
| TC_GV_MANAGE_003 | Xóa giảng viên thành công (không hướng dẫn nhóm nào) | Giảng viên "Lê Văn C" tồn tại và không hướng dẫn nhóm nào | 1. Truy cập trang quản lý giảng viên. <br> 2. Chọn GV "Lê Văn C". <br> 3. Nhấn "Xóa". <br> 4. Xác nhận xóa. | Tên GV: Lê Văn C | Hệ thống hiển thị thông báo "Xóa thành công". GV bị xóa khỏi DB và không còn hiển thị trong danh sách. | Pending |
| TC_GV_MANAGE_004 | Thêm mới giảng viên thất bại do email trùng lặp | Email "ttb@husc.edu.vn" đã tồn tại | 1. Truy cập trang quản lý giảng viên. <br> 2. Nhấn nút "Thêm mới". <br> 3. Nhập thông tin giảng viên với email đã tồn tại. <br> 4. Nhấn "Lưu". | Tên GV: Phạm Văn D, Email: ttb@husc.edu.vn, Số lượng nhóm: 3 | Hệ thống hiển thị thông báo lỗi "Email giảng viên đã tồn tại". GV không được thêm. | Pending |

## 2. Quản lý đề tài đồ án (Project Topic Management)

### 2.1. Danh sách các đề tài tham khảo (List of Reference Topics)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_TOPIC_REF_001 | Hiển thị danh sách đề tài tham khảo thành công | Có ít nhất 1 đề tài tham khảo trong DB | 1. Đăng nhập với vai trò SV/GV. <br> 2. Truy cập trang danh sách đề tài tham khảo. | N/A | Hệ thống hiển thị danh sách các đề tài, bao gồm tên và mô tả ngắn gọn. | Pending |
| TC_TOPIC_REF_002 | Tìm kiếm đề tài theo từ khóa thành công | Có đề tài "Hệ thống quản lý thư viện" trong DB | 1. Đăng nhập với vai trò SV/GV. <br> 2. Truy cập trang danh sách đề tài tham khảo. <br> 3. Nhập từ khóa "thư viện" vào ô tìm kiếm. <br> 4. Nhấn "Tìm kiếm". | Từ khóa: thư viện | Hệ thống hiển thị các đề tài có chứa từ khóa "thư viện". | Pending |
| TC_TOPIC_REF_003 | Tìm kiếm đề tài không có kết quả | Không có đề tài nào chứa từ khóa "blockchain" | 1. Đăng nhập với vai trò SV/GV. <br> 2. Truy cập trang danh sách đề tài tham khảo. <br> 3. Nhập từ khóa "blockchain" vào ô tìm kiếm. <br> 4. Nhấn "Tìm kiếm". | Từ khóa: blockchain | Hệ thống hiển thị thông báo "Không tìm thấy đề tài nào phù hợp" hoặc danh sách trống. | Pending |

### 2.2. Sinh viên/nhóm đăng ký đề tài (Student/Group Topic Registration)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_TOPIC_REG_001 | Đăng ký đề tài từ danh sách thành công | Nhóm "Nhóm Đồ án 1" chưa có đề tài, đề tài "Phát triển ứng dụng di động" khả dụng | 1. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 1". <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn đề tài "Phát triển ứng dụng di động" từ danh sách. <br> 4. Nhấn "Đăng ký". | Tên đề tài: Phát triển ứng dụng di động | Hệ thống hiển thị thông báo "Đăng ký đề tài thành công". Đề tài được gán cho "Nhóm Đồ án 1". | Pending |
| TC_TOPIC_REG_002 | Đăng ký đề tài từ danh sách thất bại (đề tài đã có nhóm đăng ký) | Nhóm "Nhóm Đồ án 2" chưa có đề tài, đề tài "Hệ thống quản lý sinh viên" đã được nhóm khác đăng ký | 1. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 2". <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn đề tài "Hệ thống quản lý sinh viên" từ danh sách. <br> 4. Nhấn "Đăng ký". | Tên đề tài: Hệ thống quản lý sinh viên | Hệ thống hiển thị thông báo lỗi "Đề tài đã có nhóm đăng ký". Đề tài không được gán cho "Nhóm Đồ án 2". | Pending |
| TC_TOPIC_REG_003 | Đề xuất đề tài mới thành công (được giảng viên duyệt) | Nhóm "Nhóm Đồ án 3" chưa có đề tài | 1. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 3". <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn "Đề xuất đề tài mới". <br> 4. Nhập thông tin đề xuất. <br> 5. Nhấn "Gửi đề xuất". <br> 6. Đăng nhập với vai trò Giảng viên. <br> 7. Duyệt đề xuất của "Nhóm Đồ án 3". | Tên đề tài: Hệ thống gợi ý phim, Mô tả: ..., Mục tiêu: ..., Công nghệ: ... | Hệ thống hiển thị thông báo "Đề xuất đã được gửi, chờ duyệt". Sau khi duyệt, đề tài được gán cho "Nhóm Đồ án 3". | Pending |
| TC_TOPIC_REG_004 | Đề xuất đề tài mới thất bại (bị giảng viên từ chối) | Nhóm "Nhóm Đồ án 4" chưa có đề tài | 1. Đăng nhập với vai trò nhóm trưởng "Nhóm Đồ án 4". <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn "Đề xuất đề tài mới". <br> 4. Nhập thông tin đề xuất. <br> 5. Nhấn "Gửi đề xuất". <br> 6. Đăng nhập với vai trò Giảng viên. <br> 7. Từ chối đề xuất của "Nhóm Đồ án 4". | Tên đề tài: Ứng dụng quản lý chi tiêu, Mô tả: ..., Mục tiêu: ..., Công nghệ: ... | Hệ thống hiển thị thông báo "Đề xuất đã được gửi, chờ duyệt". Sau khi từ chối, nhóm nhận được thông báo đề xuất bị từ chối. | Pending |

### 2.3. Kiểm tra đề tài trùng lặp (Duplicate Topic Checking)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_TOPIC_DUP_001 | Hệ thống cảnh báo trùng lặp khi đăng ký đề tài đã tồn tại | Đề tài "Hệ thống quản lý sinh viên" đã tồn tại | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn đề tài "Hệ thống quản lý sinh viên" từ danh sách. <br> 4. Nhấn "Đăng ký". | Tên đề tài: Hệ thống quản lý sinh viên | Hệ thống hiển thị cảnh báo "Đề tài này đã được đăng ký bởi nhóm khác". | Pending |
| TC_TOPIC_DUP_002 | Hệ thống cảnh báo trùng lặp khi đề xuất đề tài tương tự | Đề tài "Hệ thống quản lý thư viện" đã tồn tại | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập trang đăng ký đề tài. <br> 3. Chọn "Đề xuất đề tài mới". <br> 4. Nhập tên đề tài "Phần mềm quản lý sách". <br> 5. Nhấn "Gửi đề xuất". | Tên đề tài: Phần mềm quản lý sách | Hệ thống hiển thị cảnh báo "Đề tài có thể trùng lặp với 'Hệ thống quản lý thư viện'". | Pending |

## 3. Quản lý tiến độ và báo cáo (Progress and Reporting Management)

### 3.1. Nhóm trưởng gửi email báo cáo kết quả công việc hàng tuần (Group Leader Sends Weekly Work Report Email)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_REPORT_001 | Gửi báo cáo tuần thành công | Nhóm trưởng đã đăng nhập, có giảng viên hướng dẫn | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập chức năng gửi báo cáo. <br> 3. Nhập nội dung báo cáo và đính kèm file (nếu có). <br> 4. Nhấn "Gửi báo cáo". | Nội dung báo cáo: "Hoàn thành module A...", File đính kèm: report_week1.pdf | Hệ thống hiển thị thông báo "Báo cáo đã được gửi thành công". Giảng viên hướng dẫn nhận được email báo cáo. Báo cáo được lưu vào DB. | Pending |
| TC_REPORT_002 | Gửi báo cáo tuần thất bại do lỗi gửi email | Nhóm trưởng đã đăng nhập, có giảng viên hướng dẫn, nhưng cấu hình email sai | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập chức năng gửi báo cáo. <br> 3. Nhập nội dung báo cáo. <br> 4. Nhấn "Gửi báo cáo". | Nội dung báo cáo: "Hoàn thành module B..." | Hệ thống hiển thị thông báo lỗi "Không thể gửi email báo cáo. Vui lòng thử lại sau." Báo cáo vẫn được lưu vào DB. | Pending |

### 3.2. Giảng viên/chuyên gia doanh nghiệp gửi phản hồi về kết quả công việc (Lecturer/Business Expert Provides Feedback on Work Results)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_FEEDBACK_001 | Gửi phản hồi thành công | Giảng viên/chuyên gia đã đăng nhập, có báo cáo của nhóm | 1. Đăng nhập với vai trò Giảng viên/chuyên gia. <br> 2. Truy cập báo cáo của nhóm. <br> 3. Nhập nội dung phản hồi. <br> 4. Nhấn "Gửi phản hồi". | Nội dung phản hồi: "Báo cáo tốt, cần cải thiện phần C..." | Hệ thống hiển thị thông báo "Phản hồi đã được gửi thành công". Nhóm trưởng nhận được thông báo có phản hồi mới. Phản hồi được lưu vào DB. | Pending |
| TC_FEEDBACK_002 | Gửi phản hồi thất bại do lỗi hệ thống | Giảng viên/chuyên gia đã đăng nhập, có báo cáo của nhóm, nhưng lỗi DB | 1. Đăng nhập với vai trò Giảng viên/chuyên gia. <br> 2. Truy cập báo cáo của nhóm. <br> 3. Nhập nội dung phản hồi. <br> 4. Nhấn "Gửi phản hồi". | Nội dung phản hồi: "Cần xem xét lại phần D..." | Hệ thống hiển thị thông báo lỗi "Không thể gửi phản hồi. Vui lòng thử lại sau." Phản hồi không được lưu vào DB. | Pending |

### 3.3. Kế hoạch làm việc hàng tuần (Weekly Work Plan)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_PLAN_001 | Tạo kế hoạch làm việc trong ứng dụng thành công | Nhóm trưởng đã đăng nhập | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập chức năng lập kế hoạch. <br> 3. Thêm các công việc (tên, người phụ trách, thời hạn, trạng thái). <br> 4. Nhấn "Lưu kế hoạch". | Công việc 1: Thiết kế UI (SV001, 2024-06-30, Pending), Công việc 2: Code Backend (SV002, 2024-07-05, Pending) | Hệ thống hiển thị kế hoạch làm việc với các công việc đã thêm. Kế hoạch được lưu vào DB. | Pending |
| TC_PLAN_002 | Cập nhật trạng thái công việc thành công | Kế hoạch làm việc đã tồn tại với Công việc 1 | 1. Đăng nhập với vai trò nhóm trưởng/thành viên. <br> 2. Truy cập kế hoạch làm việc. <br> 3. Thay đổi trạng thái Công việc 1 từ "Pending" sang "Completed". <br> 4. Nhấn "Lưu". | Trạng thái Công việc 1: Completed | Hệ thống hiển thị trạng thái Công việc 1 đã được cập nhật. Trạng thái được lưu vào DB. | Pending |
| TC_PLAN_003 | Liên kết Google Sheet thành công | Nhóm trưởng đã đăng nhập, có đường dẫn Google Sheet hợp lệ | 1. Đăng nhập với vai trò nhóm trưởng. <br> 2. Truy cập chức năng lập kế hoạch. <br> 3. Chọn "Liên kết Google Sheet". <br> 4. Nhập đường dẫn Google Sheet. <br> 5. Nhấn "Lưu". | Đường dẫn Google Sheet: https://docs.google.com/spreadsheets/d/abcxyz | Hệ thống hiển thị đường dẫn Google Sheet hoặc nhúng nội dung (nếu hỗ trợ). Đường dẫn được lưu vào DB. | Pending |

## 4. Quản lý thi cử (Examination Management)

### 4.1. Thông tin về hình thức thi kết thúc học phần (Information on Final Exam Format)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_EXAM_INFO_001 | Cập nhật thông tin hình thức thi thành công | Đăng nhập với vai trò Giảng viên/Quản trị viên | 1. Truy cập trang quản lý thi cử. <br> 2. Nhập/sửa thông tin hình thức thi. <br> 3. Nhấn "Lưu". | Hình thức thi: "Thi vấn đáp trực tuyến qua Google Meet" | Hệ thống hiển thị thông báo "Cập nhật thành công". Thông tin hình thức thi được lưu vào DB và hiển thị cho sinh viên. | Pending |
| TC_EXAM_INFO_002 | Sinh viên xem thông tin hình thức thi thành công | Thông tin hình thức thi đã được cập nhật | 1. Đăng nhập với vai trò Sinh viên. <br> 2. Truy cập trang thông tin thi cử. | N/A | Hệ thống hiển thị thông tin về hình thức thi kết thúc học phần. | Pending |

### 4.2. Quản lý điểm quá trình học tập (Management of Continuous Assessment Scores)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_SCORE_MANAGE_001 | Nhập điểm quá trình thành công | Giảng viên đã đăng nhập, có nhóm/sinh viên để nhập điểm | 1. Đăng nhập với vai trò Giảng viên. <br> 2. Truy cập trang quản lý điểm. <br> 3. Chọn nhóm/sinh viên. <br> 4. Nhập điểm cho các thành phần (báo cáo, sản phẩm, thái độ). <br> 5. Nhấn "Lưu điểm". | Điểm báo cáo: 8, Điểm sản phẩm: 9, Điểm thái độ: 7 | Hệ thống hiển thị thông báo "Nhập điểm thành công". Điểm tổng kết quá trình được tính toán và hiển thị. | Pending |
| TC_SCORE_MANAGE_002 | Sinh viên xem điểm quá trình thành công | Điểm quá trình đã được nhập cho sinh viên | 1. Đăng nhập với vai trò Sinh viên. <br> 2. Truy cập trang xem điểm. | N/A | Hệ thống hiển thị điểm quá trình tổng kết của sinh viên. | Pending |

## 5. Thông báo và nhắc nhở (Notifications and Reminders)

### 5.1. Nhắc nhở hạn đăng ký (Registration Deadline Reminders)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_REMINDER_001 | Hệ thống gửi nhắc nhở hạn đăng ký nhóm | Gần đến hạn đăng ký nhóm (ví dụ: còn 3 ngày) | 1. Hệ thống tự động kiểm tra các hạn đăng ký. | N/A | Hệ thống gửi thông báo/email nhắc nhở đến các sinh viên chưa có nhóm về hạn đăng ký nhóm. | Pending |
| TC_REMINDER_002 | Hệ thống gửi nhắc nhở hạn đăng ký đề tài | Gần đến hạn đăng ký đề tài (ví dụ: còn 3 ngày) | 1. Hệ thống tự động kiểm tra các hạn đăng ký. | N/A | Hệ thống gửi thông báo/email nhắc nhở đến các nhóm chưa đăng ký đề tài về hạn đăng ký đề tài. | Pending |

### 5.2. Thông báo chung (General Notifications)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_NOTIF_001 | Quản trị viên gửi thông báo chung thành công | Đăng nhập với vai trò Quản trị viên | 1. Truy cập chức năng gửi thông báo chung. <br> 2. Nhập tiêu đề và nội dung thông báo. <br> 3. Nhấn "Gửi". | Tiêu đề: "Thông báo bảo trì hệ thống", Nội dung: "Hệ thống sẽ bảo trì từ..." | Hệ thống hiển thị thông báo "Gửi thông báo thành công". Tất cả người dùng nhận được thông báo. | Pending |
| TC_NOTIF_002 | Người dùng xem thông báo chung | Có thông báo chung mới | 1. Đăng nhập với bất kỳ vai trò nào. <br> 2. Truy cập trang thông báo. | N/A | Hệ thống hiển thị danh sách các thông báo chung. | Pending |

## 6. Phân quyền (Role-based Access Control)

### 6.1. Quyền truy cập của Sinh viên (Student Access Rights)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_ROLE_SV_001 | Sinh viên truy cập các chức năng được phép | Đăng nhập với vai trò Sinh viên | 1. Truy cập trang đăng ký thông tin cá nhân. <br> 2. Truy cập trang đăng ký nhóm. <br> 3. Truy cập trang đăng ký đề tài. <br> 4. Truy cập trang xem điểm. | N/A | Sinh viên có thể truy cập và thực hiện các chức năng này thành công. | Pending |
| TC_ROLE_SV_002 | Sinh viên không thể truy cập các chức năng không được phép | Đăng nhập với vai trò Sinh viên | 1. Cố gắng truy cập trang quản lý giảng viên (chỉ dành cho Quản trị viên). <br> 2. Cố gắng truy cập trang quản lý điểm (chỉ dành cho Giảng viên/Quản trị viên). | N/A | Hệ thống hiển thị thông báo lỗi "Không có quyền truy cập" hoặc chuyển hướng về trang chính. | Pending |

### 6.2. Quyền truy cập của Giảng viên (Lecturer Access Rights)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_ROLE_GV_001 | Giảng viên truy cập các chức năng được phép | Đăng nhập với vai trò Giảng viên | 1. Truy cập trang quản lý sinh viên/nhóm. <br> 2. Truy cập trang quản lý đề tài. <br> 3. Truy cập trang quản lý điểm. <br> 4. Truy cập báo cáo của nhóm. | N/A | Giảng viên có thể truy cập và thực hiện các chức năng này thành công. | Pending |
| TC_ROLE_GV_002 | Giảng viên không thể truy cập các chức năng không được phép | Đăng nhập với vai trò Giảng viên | 1. Cố gắng truy cập chức năng thêm/sửa/xóa giảng viên (chỉ dành cho Quản trị viên). <br> 2. Cố gắng truy cập chức năng gửi thông báo chung (chỉ dành cho Quản trị viên). | N/A | Hệ thống hiển thị thông báo lỗi "Không có quyền truy cập" hoặc chuyển hướng về trang chính. | Pending |

### 6.3. Quyền truy cập của Quản trị viên (Administrator Access Rights)

| Test Case ID | Mô tả (Description) | Điều kiện tiên quyết (Pre-conditions) | Bước thực hiện (Steps) | Dữ liệu đầu vào (Input Data) | Kết quả mong muốn (Expected Result) | Trạng thái (Status) |
|---|---|---|---|---|---|---|
| TC_ROLE_ADMIN_001 | Quản trị viên truy cập tất cả các chức năng | Đăng nhập với vai trò Quản trị viên | 1. Truy cập tất cả các trang và chức năng của hệ thống. | N/A | Quản trị viên có thể truy cập và thực hiện tất cả các chức năng thành công. | Pending |