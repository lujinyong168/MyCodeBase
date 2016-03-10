package com.imf.multipleThread.thread3.service;

import java.util.List;

import com.imf.multipleThread.thread3.entry.Effective;

public interface EffectiveService {

	void writeToDB(List<Effective> writeList);

}
