# Distributed under the OSI-approved BSD 3-Clause License.  See accompanying
# file Copyright.txt or https://cmake.org/licensing for details.

cmake_minimum_required(VERSION 3.5)

file(MAKE_DIRECTORY
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-src"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-build"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/tmp"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/src/doctest-populate-stamp"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/src"
  "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/src/doctest-populate-stamp"
)

set(configSubDirs )
foreach(subDir IN LISTS configSubDirs)
    file(MAKE_DIRECTORY "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/src/doctest-populate-stamp/${subDir}")
endforeach()
if(cfgdir)
  file(MAKE_DIRECTORY "/Users/chris/Documents/Course/FA23/数据结构与算法/Lab/Lab01/cmake-build-debug/_deps/doctest-subbuild/doctest-populate-prefix/src/doctest-populate-stamp${cfgdir}") # cfgdir has leading slash
endif()
