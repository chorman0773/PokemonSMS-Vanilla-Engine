package github.chorman0773.pokemonsms.lua;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.luaj.vm2.lib.ResourceFinder;

public class CoreLibraryLocator implements ResourceFinder {
	private Path baseDir;
	public CoreLibraryLocator(Path baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	public InputStream findResource(String filename) {
		try {
			Path libDir = baseDir.resolve("lib/lua");
			Path modsDir = baseDir.resolve("mods/");
			for(Path p:Files.newDirectoryStream(libDir)) {
				Path target= p.resolve(filename+".lua");
				if(Files.exists(target))
					return Files.newInputStream(p);
			}
			if(Files.exists(modsDir))
				for(Path mod:Files.newDirectoryStream(modsDir)) {
					Path target = mod.resolve(filename+".lua");
					if(Files.exists(target))
						return Files.newInputStream(target);
				}
			return null;
		}catch(IOException e) {
			return null;
		}	
	}

}
