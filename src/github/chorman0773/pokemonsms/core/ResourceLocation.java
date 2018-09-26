package github.chorman0773.pokemonsms.core;

import java.util.Set;

import github.lightningcreations.lclib.Hash;

public final class ResourceLocation implements Comparable<ResourceLocation> {
	private String domain, path;
	private ResourceLocation(String[] str) {
		this(str[0],str[1]);
	}
	public ResourceLocation(String name) {
		this(name.split(":"));
	}
	public ResourceLocation(String domain,String path) {
		this.domain = domain;
		this.path = path;
	}
	@Override
	public int compareTo(ResourceLocation o) {
		int comp;
		if((comp =domain.compareToIgnoreCase(o.domain))!=0)
			return comp;
		return path.compareToIgnoreCase(o.path);
	}

	@Override
	public int hashCode() {
		return Hash.hashcode(domain)*31+Hash.hashcode(path);
	}
	
	public boolean isReserved() {
		return Registry.Reserved.domains.contains(domain);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null)
			return false;
		else if(o==this)
			return true;
		else if(o.getClass()!=ResourceLocation.class)
			return false;
		else
			return compareTo((ResourceLocation)o)==0;
	}
	public String getDomain() {
		return domain;
	}
	public String getPath() {
		return path;
	}
	public String toString() {
		return domain+":"+path;
	}
}
