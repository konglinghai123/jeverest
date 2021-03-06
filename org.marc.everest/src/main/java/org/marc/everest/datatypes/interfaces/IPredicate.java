/* 
 * Copyright 2008-2011 Mohawk College of Applied Arts and Technology
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you 
 * may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under 
 * the License.
 * 
 * User: Justin Fyfe
 * Date: 09-27-2012
 */
package org.marc.everest.datatypes.interfaces;

/**
 * A Java implementation of the .NET Predicate<> interface. Used for searching sets
 */
public interface IPredicate<T> {
	
	
	/**
	 * Returns true if the predicate function matches the 
	 * @param i
	 * @return
	 */
	boolean match(T i);
	
}
