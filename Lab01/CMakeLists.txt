cmake_minimum_required(VERSION 3.26)
project(Lab01)

# C++17 编译标准
set(CMAKE_CXX_STANDARD 17)
# 设置根目录
set(ROOT_DIR ${CMAKE_SOURCE_DIR})

# 添加头文件
include_directories(${ROOT_DIR}/include)
# 添加外部引用文件的搜索路径
link_directories(${ROOT_DIR}/lib)
# 添加源文件
aux_source_directory(${ROOT_DIR}/src DIR_SRC)
aux_source_directory(${ROOT_DIR}/src/gtest DIR_GTEST)
aux_source_directory(${ROOT_DIR}/src/gmock DIR_GMOCK)

# 拉取 Google Test
include(FetchContent)
FetchContent_Declare(
        googletest
        URL https://github.com/google/googletest/archive/5376968f6948923e2411081fd9372e71a59d8e77.zip
)
# For Windows: Prevent overriding the parent project's compiler/linker settings
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

# 需要编译的源文件
add_executable(Lab01
        ${DIR_SRC}
        ${DIR_GTEST}
        ${DIR_GMOCK}
)
# 链接 Google Test 外部库
target_link_libraries(Lab01 gtest_main)

add_test(NAME example_test COMMAND example)