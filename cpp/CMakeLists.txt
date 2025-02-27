cmake_minimum_required(VERSION 3.22.1)
project("skaI")

set(CMAKE_CXX_STANDARD 17)

include_directories(${CMAKE_SOURCE_DIR}/cpp)

# Add llama.cpp sources
file(GLOB llama_cpp_sources "${CMAKE_SOURCE_DIR}/cpp/llama.cpp/*.cpp")
file(GLOB ggml_sources "${CMAKE_SOURCE_DIR}/cpp/ggml*.c")
file(GLOB ggml_hpp_sources "${CMAKE_SOURCE_DIR}/cpp/ggml*.h")

# Add Vulkan.hpp headers
include_directories(${CMAKE_SOURCE_DIR}/cpp)

find_package(Vulkan REQUIRED)

include_directories(${Vulkan_INCLUDE_DIRS})

add_library(
        skaI
        SHARED
        src/main/cpp/native-lib.cpp
        ${llama_cpp_sources}
        ${ggml_sources}
        ${ggml_hpp_sources}
)

target_link_libraries(
        skaI
        Vulkan::Vulkan
)

set_target_properties(skaI PROPERTIES LINKER_LANGUAGE CXX)