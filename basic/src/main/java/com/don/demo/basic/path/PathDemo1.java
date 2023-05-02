package com.don.demo.basic.path;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @date 2019年11月04日 下午 12:36
 */
public class PathDemo1 {
	//创建path对象
	@Test
	public void tes1() {
		Path path = Paths.get("D:\\Users\\xxx\\Desktop\\..\\svn-config.properties");
		Path path1 = path.normalize();
		System.out.println(path);
		System.out.println(path1);
	}

	//相对路径
	@Test
	public void test2() {
		Path projects = Paths.get("d:\\data", "projects");
		Path file = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
	}

	//创建文件夹，前面的文件夹需要有
	@Test
	public void test3() {
		Path path = Paths.get("D:\\dir", "\\user");
		try {
			Path newDir = Files.createDirectory(path);
		} catch (FileAlreadyExistsException e) {
			// the directory already exists.
		} catch (IOException e) {
			//something else went wrong
			e.printStackTrace();
		}
	}

	//拷贝文件
	@Test
	public void test5() {
		Path sourcePath = Paths.get("data/logging.properties");
		Path destinationPath = Paths.get("data/logging-copy.properties");

		try {
			Files.copy(sourcePath, destinationPath);
		} catch (FileAlreadyExistsException e) {
			//destination file already exists
		} catch (IOException e) {
			//something else went wrong
			e.printStackTrace();
		}
	}

	//覆盖文件
	@Test
	public void test6() {
		Path sourcePath = Paths.get("data/logging.properties");
		Path destinationPath = Paths.get("data/logging-copy.properties");

		try {
			Files.copy(sourcePath, destinationPath,
					StandardCopyOption.REPLACE_EXISTING);
		} catch (FileAlreadyExistsException e) {
			//destination file already exists
		} catch (IOException e) {
			//something else went wrong
			e.printStackTrace();
		}
	}

	//移动文件
	@Test
	public void test7() {
		Path sourcePath = Paths.get("data/logging-copy.properties");
		Path destinationPath = Paths.get("data/subdir/logging-moved.properties");

		try {
			Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			//moving file failed.
			e.printStackTrace();
		}
	}

	//删除文件
	@Test
	public void test8() {
		Path path = Paths.get("data/subdir/logging-moved.properties");
		try {
			Files.delete(path);
		} catch (IOException e) {
			//deleting file failed
			e.printStackTrace();
		}
	}

	//遍历文件夹
	@Test
	public void test9() throws IOException {
		Path path = Paths.get("D:\\logs");
		Files.walkFileTree(path, new FileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("pre visit dir:" + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("visit file: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println("visit file failed: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				System.out.println("post visit dir: " + dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}

	//查找文件
	@Test
	public void test10() {
		Path root = Paths.get("D:\\logs");
		//final String fileToFind = File.separator+"log";
		final String fileToFind = ".log";
		try {
			Files.walkFileTree(root, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String fileString = file.toAbsolutePath().toString();
					//System.out.println("pathString = " + fileString);

					if (fileString.endsWith(fileToFind)) {
						System.out.println("file found at path: " + file.toAbsolutePath());
						return FileVisitResult.TERMINATE;
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//循环删除文件
	@Test
	public void test11() {
		Path rootPath = Paths.get("data/to-delete");

		try {
			Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println("delete file: " + file.toString());
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					System.out.println("delete dir: " + dir.toString());
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//生成超链接
	@Test
	public void test12() throws IOException {
		//连接路径
		Path p = Paths.get("D:\\data\\1.txt");
		//目标路径
		Path target = Paths.get("D:\\logs\\data");
		//生成指向目标路径的超链接,返回连接路径p
		Path p3 = Files.createSymbolicLink(p, target);
	}

	//读取文件内容
	@Test
	public void test13() throws IOException {
		//读取每行数据放入List中
		List<String> lines = Files.readAllLines(Paths.get("data//1.txt"));
		for (String line : lines) {
			System.out.println(line);
		}
		//读取文件内容放入流中
		Stream<String> line = Files.lines(Paths.get("data/1.txt"));
	}

}
