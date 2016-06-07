package com.klaus.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keans {

	private int KNumber;
	private double[] data;

	public Keans(double[] data) {

		this.data=data;
		
		this.KNumber=6;
		
	}

	public Keans(int KNumber, double[] data) {

		this.KNumber=KNumber;
		this.data=data;
		
	}

	public List<Map<String, Double>> classifyData() {

		List<Map<String, Double>> list = new ArrayList<Map<String, Double>>();

		double[][] g;

		g = cluster(data, KNumber);

		for (int i = 0; i < g.length; i++) {

			Map<String, Double> map = new HashMap<String, Double>();

			double max = 0, min = 0, sum = 0, mean = 0;

			for (int j = 0; j < g[i].length; j++) {

				if (j == 0) {

					max = g[i][j];
					min = g[i][j];

				}

				sum = sum + g[i][j];

				if (g[i][j] > max) {

					max = g[i][j];

				}

				if (g[i][j] < min) {

					min = g[i][j];

				}

				System.out.print(g[i][j]);
				System.out.print("\t");

			}

			if (g[i].length != 0) {

				mean = sum / (g[i].length + 0);

			}

			map.put("maxk", max);
			map.put("mink", min);
			map.put("meank", mean);

			list.add(map);

			System.out.println();

		}

		return list;

	}

	/*
	 * 聚类函数主体。 针对一维 double 数组。指定聚类数目 k。 将数据聚成 k 类。
	 */
	private double[][] cluster(double[] p, int k) {
		// 存放聚类旧的聚类中心
		double[] c = new double[k];
		// 存放新计算的聚类中心
		double[] nc = new double[k];
		// 存放放回结果
		double[][] g;
		// 初始化聚类中心
		// 经典方法是随机选取 k 个
		// 本例中采用前 k 个作为聚类中心
		// 聚类中心的选取不影响最终结果
		for (int i = 0; i < k; i++)
			c[i] = p[i];
		// 循环聚类，更新聚类中心
		// 到聚类中心不变为止
		while (true) {
			// 根据聚类中心将元素分类
			g = group(p, c);
			// 计算分类后的聚类中心
			for (int i = 0; i < g.length; i++) {
				nc[i] = center(g[i]);
			}
			// 如果聚类中心不同
			if (!equal(nc, c)) {
				// 为下一次聚类准备
				c = nc;
				nc = new double[k];
			} else // 聚类结束
				break;
		}
		// 返回聚类结果
		return g;
	}

	/*
	 * 聚类中心函数 简单的一维聚类返回其算数平均值 可扩展
	 */
	private double center(double[] p) {
		return sum(p) / p.length;
	}

	/*
	 * 给定 double 型数组 p 和聚类中心 c。 根据 c 将 p 中元素聚类。返回二维数组。 存放各组元素。
	 */
	private double[][] group(double[] p, double[] c) {
		// 中间变量，用来分组标记
		int[] gi = new int[p.length];
		// 考察每一个元素 pi 同聚类中心 cj 的距离
		// pi 与 cj 的距离最小则归为 j 类
		for (int i = 0; i < p.length; i++) {
			// 存放距离
			double[] d = new double[c.length];
			// 计算到每个聚类中心的距离
			for (int j = 0; j < c.length; j++) {
				d[j] = distance(p[i], c[j]);
			}
			// 找出最小距离，返回最小值的下标
			int ci = min(d);
			// 标记属于哪一组
			gi[i] = ci;
		}
		// 存放分组结果
		double[][] g = new double[c.length][];
		// 遍历每个聚类中心，分组
		for (int i = 0; i < c.length; i++) {
			// 中间变量，记录聚类后每一组的大小
			int s = 0;
			// 计算每一组的长度
			for (int j = 0; j < gi.length; j++)
				if (gi[j] == i)
					s++;
			// 存储每一组的成员
			g[i] = new double[s];
			s = 0;
			// 根据分组标记将各元素归位
			for (int j = 0; j < gi.length; j++)
				if (gi[j] == i) {
					g[i][s] = p[j];
					s++;
				}
		}
		// 返回分组结果
		return g;
	}

	/*
	 * 计算两个点之间的距离， 这里采用最简单得一维欧氏距离， 可扩展。
	 */
	private double distance(double x, double y) {
		return Math.abs(x - y);
	}

	/*
	 * 返回给定 double 数组各元素之和。
	 */
	private double sum(double[] p) {
		double sum = 0.0;
		for (int i = 0; i < p.length; i++)
			sum += p[i];
		return sum;
	}

	/*
	 * 给定 double 类型数组，返回最小值得下标。
	 */
	private int min(double[] p) {
		int i = 0;
		double m = p[0];
		for (int j = 1; j < p.length; j++) {
			if (p[j] < m) {
				i = j;
				m = p[j];
			}
		}
		return i;
	}

	/*
	 * 判断两个 double 数组是否相等。 长度一样且对应位置值相同返回真。
	 */
	private boolean equal(double[] a, double[] b) {
		if (a.length != b.length)
			return false;
		else {
			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i])
					return false;
			}
		}
		return true;
	}
}